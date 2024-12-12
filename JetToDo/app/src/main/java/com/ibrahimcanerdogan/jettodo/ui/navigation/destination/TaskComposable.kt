package com.ibrahimcanerdogan.jettodo.ui.navigation.destination

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.ibrahimcanerdogan.jettodo.ui.navigation.Screen
import com.ibrahimcanerdogan.jettodo.ui.screen.TaskScreen
import com.ibrahimcanerdogan.jettodo.ui.viewmodel.BaseViewModel
import com.ibrahimcanerdogan.jettodo.utils.Action

fun NavGraphBuilder.taskComposable(
    viewModel: BaseViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    composable<Screen.Task>(
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> -fullWidth },
                animationSpec = tween(durationMillis = 300)
            )
        }
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.toRoute<Screen.Task>().id
        LaunchedEffect(key1 = taskId) {
            viewModel.getSelectedTask(taskId = taskId)
        }

        val selectedTask by viewModel.selectedTask.collectAsState()
        LaunchedEffect(key1 = selectedTask) {
            if (selectedTask != null || taskId == -1) {
                viewModel.updateTaskFields(selectedTask = selectedTask)
            }
        }

        TaskScreen(
            selectedTask = selectedTask,
            viewModel = viewModel,
            navigateToListScreen = navigateToListScreen
        )
    }
}