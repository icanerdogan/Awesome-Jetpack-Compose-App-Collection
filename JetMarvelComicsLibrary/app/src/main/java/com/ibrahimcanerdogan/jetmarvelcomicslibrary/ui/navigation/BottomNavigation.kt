package com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.navigation

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.R

@Composable
fun BottomNavigation(navController: NavHostController) {
    BottomAppBar(
        actions = {
            IconButton(
                onClick = { navController.navigate(Destination.CharacterList.route) {
                    popUpTo(Destination.CharacterList.route)
                    launchSingleTop = true
                } }
            ) {
                Icon(painter = painterResource(R.drawable.icon_collection), null)
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Destination.Library.route) {
                    launchSingleTop = true
                } },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(painter = painterResource(R.drawable.icon_library), null)
            }
        }
    )
}