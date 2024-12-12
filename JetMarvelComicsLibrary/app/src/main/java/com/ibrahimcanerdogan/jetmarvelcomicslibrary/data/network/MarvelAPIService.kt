package com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.network

import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.model.CharacterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelAPIService {

    // https://developer.marvel.com/docs

    @GET("characters")
    fun getCharacterNetwork(
        @Query("nameStartsWith") charName: String
    ): Call<CharacterResponse>
}