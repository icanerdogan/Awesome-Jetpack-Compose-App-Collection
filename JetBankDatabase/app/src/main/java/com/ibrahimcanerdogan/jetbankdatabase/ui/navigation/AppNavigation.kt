package com.ibrahimcanerdogan.jetbankdatabase.ui.navigation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ibrahimcanerdogan.jetbankdatabase.ui.view.detail.DetailScreen
import com.ibrahimcanerdogan.jetbankdatabase.ui.view.home.HomeScreen
import com.ibrahimcanerdogan.jetbankdatabase.ui.view.splash.SplashScreen

@Composable
fun AppNavigation(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = AppScreen.SPLASH_SCREEN.name
    ) {
        composable(
            route = AppScreen.SPLASH_SCREEN.name
        ) {
            SplashScreen(navHostController)
        }
        composable(
            route = AppScreen.HOME_SCREEN.name,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -1000 },
                    animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                )
            }
        ) {
            HomeScreen(navHostController)
        }
        composable(
            route = "${AppScreen.DETAIL_SCREEN.name}/{bankDataJson}",
            arguments = listOf(navArgument("bankDataJson"){
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val bankDataJson = backStackEntry.arguments?.getString("bankDataJson")
            bankDataJson?.let {
                DetailScreen(it)
            }
        }
    }
}