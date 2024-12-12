package com.ibrahimcanerdogan.jettodo.dependencyinjection

import android.content.Context
import com.ibrahimcanerdogan.jettodo.data.database.ToDoDao
import com.ibrahimcanerdogan.jettodo.data.repository.DataStoreRepositoryImpl
import com.ibrahimcanerdogan.jettodo.data.repository.TodoRepositoryImpl
import com.ibrahimcanerdogan.jettodo.domain.repository.DataStoreRepository
import com.ibrahimcanerdogan.jettodo.domain.repository.ToDoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideToDoRepository(
        toDoDao: ToDoDao
    ) : ToDoRepository {
        return TodoRepositoryImpl(toDoDao)
    }

    @Singleton
    @Provides
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ) : DataStoreRepository {
        return DataStoreRepositoryImpl(context)
    }
}