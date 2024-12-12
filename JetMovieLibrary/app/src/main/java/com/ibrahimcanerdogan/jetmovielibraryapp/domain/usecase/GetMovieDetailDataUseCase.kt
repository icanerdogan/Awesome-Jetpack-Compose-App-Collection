package com.ibrahimcanerdogan.jetmovielibraryapp.domain.usecase

import com.ibrahimcanerdogan.jetmovielibraryapp.domain.model.MovieDetailData
import com.ibrahimcanerdogan.jetmovielibraryapp.domain.repository.MovieRepository
import com.ibrahimcanerdogan.jetmovielibraryapp.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailDataUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend fun execute(movieImdbID: String): Flow<Resource<MovieDetailData>> {
       return movieRepository.getMovieDetailData(movieImdbID)
    }
}