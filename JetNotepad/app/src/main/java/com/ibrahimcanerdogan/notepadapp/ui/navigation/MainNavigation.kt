package com.ibrahimcanerdogan.notepadapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ibrahimcanerdogan.notepadapp.ui.screen.NoteAddScreen
import com.ibrahimcanerdogan.notepadapp.ui.screen.NoteListScreen
import com.ibrahimcanerdogan.notepadapp.ui.viewmodel.NoteViewModel

@Composable
fun MainNavigation(
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    val controller = rememberNavController()
    val noteViewModel: NoteViewModel = hiltViewModel()

    NavHost(navController = controller, startDestination = AppScreen.NOTE_LIST.name) {
        composable(AppScreen.NOTE_LIST.name) {
            NoteListScreen(
                viewModel = noteViewModel,
                navController = controller,
                isDarkTheme = isDarkTheme,
                onThemeChange = onThemeChange
            )
        }
        composable(AppScreen.NOTE_ADD.name) {
            NoteAddScreen(
                viewModel = noteViewModel,
                navController = controller
            )
        }
    }
}