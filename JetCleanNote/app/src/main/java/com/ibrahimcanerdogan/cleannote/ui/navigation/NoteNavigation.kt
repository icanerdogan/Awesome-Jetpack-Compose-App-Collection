package com.ibrahimcanerdogan.cleannote.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ibrahimcanerdogan.cleannote.ui.view.edit.AddEditNoteScreen
import com.ibrahimcanerdogan.cleannote.ui.view.list.NoteListScreen

@Composable
fun NoteNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NoteScreen.NotesScreen.route
    ) {
        composable(route = NoteScreen.NotesScreen.route) {
            NoteListScreen(navController = navController)
        }
        composable(
            route = NoteScreen.AddEditNoteScreen.route + "?noteId={noteId}&noteColor={noteColor}",
            arguments = listOf(
                navArgument(
                    name = "noteId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(
                    name = "noteColor"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
            )
        ) {
            val color = it.arguments?.getInt("noteColor") ?: -1
            AddEditNoteScreen(
                navController = navController,
                noteColor = color
            )
        }
    }
}