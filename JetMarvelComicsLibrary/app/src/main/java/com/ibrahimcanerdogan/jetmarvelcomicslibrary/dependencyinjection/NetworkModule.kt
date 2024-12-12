package com.ibrahimcanerdogan.jetmarvelcomicslibrary.dependencyinjection

import android.content.Context
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.BuildConfig
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.connectivity.ConnectivityMonitor
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.network.MarvelAPIService
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.utils.Constants
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.utils.HashUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context) = ConnectivityMonitor.getInstance(context)

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): MarvelAPIService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(MarvelAPIService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(clientInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(clientInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideHttpInterceptor(): Interceptor {
        val ts = System.currentTimeMillis().toString()
        val apiSecret = BuildConfig.MARVEL_SECRET
        val apiKey = BuildConfig.MARVEL_KEY
        val hash = HashUtil.getHash(ts, apiSecret, apiKey)

        return Interceptor { chain ->
            var request: Request = chain.request()
            val url: HttpUrl = request.url.newBuilder()
                .addQueryParameter("ts", ts)
                .addQueryParameter("apikey", apiKey)
                .addQueryParameter("hash", hash)
                .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }
    }

}