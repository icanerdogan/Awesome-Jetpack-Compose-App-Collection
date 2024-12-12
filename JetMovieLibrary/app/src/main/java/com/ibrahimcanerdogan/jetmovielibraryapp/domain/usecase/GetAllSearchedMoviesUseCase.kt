package com.ibrahimcanerdogan.jetmovielibraryapp.domain.usecase

import com.ibrahimcanerdogan.jetmovielibraryapp.domain.model.MovieListData
import com.ibrahimcanerdogan.jetmovielibraryapp.domain.repository.MovieRepository
import com.ibrahimcanerdogan.jetmovielibraryapp.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSearchedMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend fun execute(searchText: String): Flow<Resource<List<MovieListData>>> {
        return movieRepository.getAllSearchedMovies(searchText)
    }
}