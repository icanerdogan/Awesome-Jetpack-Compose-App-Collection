package com.ibrahimcanerdogan.contactsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.ibrahimcanerdogan.contactsapp.data.model.Person
import com.ibrahimcanerdogan.contactsapp.ui.screen.PersonDetailScreen
import com.ibrahimcanerdogan.contactsapp.ui.screen.PersonMainScreen
import com.ibrahimcanerdogan.contactsapp.ui.screen.PersonRegisterScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            PersonMainScreen(navController = navController)
        }
        composable("register") {
            PersonRegisterScreen(navController = navController)
        }
        composable("detail/{person}", arguments = listOf(navArgument("person") { type = NavType.StringType })) {
            val json = it.arguments?.getString("person")
            val person = Gson().fromJson(json, Person::class.java)
            PersonDetailScreen(person = person, navController = navController)
        }
    }
}