package com.ibrahimcanerdogan.jettodo.ui.navigation.destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.ibrahimcanerdogan.jettodo.ui.navigation.Screen
import com.ibrahimcanerdogan.jettodo.ui.screen.ListScreen
import com.ibrahimcanerdogan.jettodo.ui.viewmodel.BaseViewModel
import com.ibrahimcanerdogan.jettodo.utils.Action

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    viewModel: BaseViewModel
) {
    composable<Screen.List> { navBackStackEntry ->
        val action = navBackStackEntry.toRoute<Screen.List>().action
        var myAction by rememberSaveable { mutableStateOf(Action.NO_ACTION) }

        LaunchedEffect(key1 = myAction) {
            if(action != myAction){
                myAction = action
                viewModel.updateAction(newAction = action)
            }
        }

        val databaseAction = viewModel.action

        ListScreen(
            action = databaseAction,
            navigateToTaskScreen = navigateToTaskScreen,
            viewModel = viewModel
        )
    }
}