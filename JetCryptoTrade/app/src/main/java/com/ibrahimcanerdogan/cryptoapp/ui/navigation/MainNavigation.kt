package com.ibrahimcanerdogan.cryptoapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ibrahimcanerdogan.cryptoapp.ui.screen.CryptoDetailScreen
import com.ibrahimcanerdogan.cryptoapp.ui.screen.CryptoListScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "crypto_list_screen") {
        composable("crypto_list_screen") {
            CryptoListScreen(navController = navController)
        }
        composable(
            route = "crypto_detail_screen/{cryptoID}",
            arguments = listOf(navArgument("cryptoID") {type = NavType.StringType})
        ) {
            val cryptoID = remember { it.arguments?.getString("cryptoID") }

            CryptoDetailScreen(cryptoID = cryptoID ?: "")
        }
    }
}