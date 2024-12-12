package com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.repository

import androidx.compose.runtime.mutableStateOf
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.model.CharacterResponse
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.model.CharacterResult
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.network.MarvelAPIService
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.network.NetworkResult
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.domain.repository.NetworkRepository
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val apiService: MarvelAPIService
): NetworkRepository {

    override val characters = MutableStateFlow<NetworkResult<CharacterResponse>>(NetworkResult.Initial())
    override val characterDetails = mutableStateOf<CharacterResult?>(null)

    override fun getMarvelCharactersRepository(query: String) {
        characters.value = NetworkResult.Loading()
        apiService.getCharacterNetwork(query)
            .enqueue(object : Callback<CharacterResponse> {
                override fun onResponse(
                    call: Call<CharacterResponse>,
                    response: Response<CharacterResponse>
                ) {
                    if (response.isSuccessful)
                        response.body()?.let {
                            characters.value = NetworkResult.Success(it)
                        }
                    else
                        characters.value = NetworkResult.Error(response.message())
                }

                override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                    t.localizedMessage?.let {
                        characters.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }

            })
    }

    override fun getSingleMarvelCharacterRepository(id: Int?) {
        id?.let {
            characterDetails.value = characters.value.data?.responseData?.results?.firstOrNull { character ->
                    character.resultId == id
                }
        }
    }
}