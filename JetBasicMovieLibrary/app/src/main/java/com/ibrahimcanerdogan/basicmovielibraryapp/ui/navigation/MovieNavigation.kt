package com.ibrahimcanerdogan.basicmovielibraryapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ibrahimcanerdogan.basicmovielibraryapp.ui.screen.DetailScreen
import com.ibrahimcanerdogan.basicmovielibraryapp.ui.screen.HomeScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MovieScreens.HomeScreen.name
    ) {
        composable(MovieScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
        composable(MovieScreens.DetailScreen.name + "/{movieID}",
            arguments = listOf(
                navArgument("movieID") { type = NavType.StringType }
            )
        ) {
            DetailScreen(
                navController = navController,
                movieId = it.arguments?.getString("movieID")
            )
        }
    }
}