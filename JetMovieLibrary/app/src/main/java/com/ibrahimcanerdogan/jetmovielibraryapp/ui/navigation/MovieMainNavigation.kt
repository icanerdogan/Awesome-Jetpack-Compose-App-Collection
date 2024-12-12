package com.ibrahimcanerdogan.jetmovielibraryapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.screen.MovieDetailScreen
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.screen.MovieFavoriteScreen
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.screen.MovieHomeScreen
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.viewmodel.favorite.MovieFavoriteViewModel
import com.ibrahimcanerdogan.jetmovielibraryapp.util.Constants

@Composable
fun MovieMainNavigation(
    navController: NavHostController = rememberNavController()
) {
    // Favori işlemi aynı instance oluşturulursa aynı anda aynı bilgilere sahip olurlar.
    val movieFavoriteViewModel: MovieFavoriteViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = MovieScreens.LIST_SCREEN.name) {
        composable(route =  MovieScreens.LIST_SCREEN.name) {
            MovieHomeScreen(navController = navController)
        }
        composable(route =  MovieScreens.DETAIL_SCREEN.name + "/{${Constants.IMDB_ID}}") {
            MovieDetailScreen(viewModelFavorite = movieFavoriteViewModel)
        }
        composable(route = MovieScreens.FAVORITE_SCREEN.name) {
            MovieFavoriteScreen(navController = navController, movieFavoriteViewModel)
        }
    }
}