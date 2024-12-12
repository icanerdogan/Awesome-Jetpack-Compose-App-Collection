package com.ibrahimcanerdogan.jetmovielibraryapp.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ibrahimcanerdogan.jetmovielibraryapp.data.database.favorite.MovieFavoriteEntity
import com.ibrahimcanerdogan.jetmovielibraryapp.data.database.favorite.toMovieListData
import com.ibrahimcanerdogan.jetmovielibraryapp.domain.model.toMovieFavoriteEntity
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.navigation.MovieScreens
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.viewmodel.favorite.MovieFavoriteViewModel
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.widget.MovieListItem

@Composable
fun MovieFavoriteListView(
    navController: NavController,
    favoriteViewModel: MovieFavoriteViewModel,
    favoriteMovieList: List<MovieFavoriteEntity>
) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)
    ) {
        items(favoriteMovieList, key = { it.movieEntityImdbID }) { movieFavoriteEntity ->
            MovieListItem(
                modifier = Modifier.height(250.dp).padding(5.dp),
                titleFontSize = 15.sp,
                subtitleFontSize = 10.sp,
                isRemoveButton = true,
                movieListData =  movieFavoriteEntity.toMovieListData(),
                onItemClick = {
                    navController.navigate(MovieScreens.DETAIL_SCREEN.name + "/{${it.movieSearchImdbID}}")
                },
                onRemoveClick = { removeMovie ->
                    favoriteViewModel.deleteFavorite(removeMovie.toMovieFavoriteEntity())
                }
            )
        }
    }
}