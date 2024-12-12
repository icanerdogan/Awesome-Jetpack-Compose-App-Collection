package com.ibrahimcanerdogan.jetmarvelcomicslibrary.domain.repository

import androidx.compose.runtime.MutableState
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.model.CharacterResponse
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.model.CharacterResult
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.network.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow

interface NetworkRepository {

    val characters: MutableStateFlow<NetworkResult<CharacterResponse>>
    val characterDetails: MutableState<CharacterResult?>

    fun getMarvelCharactersRepository(query: String)
    fun getSingleMarvelCharacterRepository(id: Int?)

}