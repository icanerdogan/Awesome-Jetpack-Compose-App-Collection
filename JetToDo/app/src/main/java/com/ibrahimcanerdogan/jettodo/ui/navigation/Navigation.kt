package com.ibrahimcanerdogan.jettodo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ibrahimcanerdogan.jettodo.ui.navigation.destination.listComposable
import com.ibrahimcanerdogan.jettodo.ui.navigation.destination.taskComposable
import com.ibrahimcanerdogan.jettodo.ui.viewmodel.BaseViewModel

@Composable
fun ToDoNavigation(
    navController: NavHostController,
    viewModel: BaseViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.List()
    ) {
        listComposable(
            navigateToTaskScreen = { taskId ->
                navController.navigate(Screen.Task(id = taskId))
            },
            viewModel = viewModel
        )
        taskComposable(
            navigateToListScreen = { action ->
                navController.navigate(Screen.List(action = action)) {
                    popUpTo(Screen.List()) { inclusive = true }
                }
            },
            viewModel = viewModel
        )
    }
}