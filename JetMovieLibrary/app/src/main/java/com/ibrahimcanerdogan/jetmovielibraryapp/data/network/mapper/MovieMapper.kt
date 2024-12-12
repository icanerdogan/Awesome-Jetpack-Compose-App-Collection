package com.ibrahimcanerdogan.jetmovielibraryapp.data.network.mapper

import com.ibrahimcanerdogan.jetmovielibraryapp.data.network.dto.MovieDetailDTO
import com.ibrahimcanerdogan.jetmovielibraryapp.data.network.dto.MovieSearchDTO
import com.ibrahimcanerdogan.jetmovielibraryapp.domain.model.MovieDetailData
import com.ibrahimcanerdogan.jetmovielibraryapp.domain.model.MovieListData

object MovieMapper {

    fun MovieSearchDTO.toMovieListData() : List<MovieListData> {
        return movieSearch.map {
            MovieListData(
                it.movieSearchTitle,
                it.movieSearchYear,
                it.movieSearchImdbID,
                it.movieSearchPoster
            )
        }
    }

    fun MovieDetailDTO.toMovieDetailData() : MovieDetailData {
        return MovieDetailData(
            movieDataImdbID,
            movieDataTitle,
            movieDataYear,
            movieDataReleased,
            movieDataRuntime,
            movieDataGenre,
            movieDataDirector,
            movieDataWriter,
            movieDataActors,
            movieDataPlot,
            movieDataLanguage,
            movieDataCountry,
            movieDataPoster,
            movieDataRatings,
            movieDataImdbRating,
            movieDataImdbVotes
        )
    }
}