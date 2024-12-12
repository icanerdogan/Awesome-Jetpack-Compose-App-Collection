package com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.screen.CharacterDetailScreen
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.screen.LibraryScreen
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.screen.CharacterListScreen
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.viewmodel.LibraryViewModel
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.viewmodel.CharacterListViewModel


@Composable
fun Navigation(
    navController: NavHostController,
    characterListViewModel: CharacterListViewModel,
    libraryViewModel: LibraryViewModel
) {
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = Destination.CharacterList.route) {
        composable(Destination.CharacterList.route) {
            CharacterListScreen(navController, characterListViewModel)
        }
        composable(Destination.Library.route) {
            LibraryScreen(libraryViewModel)
        }
        composable(Destination.CharacterDetail.route) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("characterId")?.toIntOrNull()
            if (id == null)
                Toast.makeText(context, "Character ID is required!", Toast.LENGTH_SHORT).show()
            else {
                characterListViewModel.retrieveSingleCharacter(id)
                CharacterDetailScreen(
                    navController = navController,
                    characterListViewModel = characterListViewModel,
                    libraryViewModel = libraryViewModel,
                )
            }
        }
    }
}
