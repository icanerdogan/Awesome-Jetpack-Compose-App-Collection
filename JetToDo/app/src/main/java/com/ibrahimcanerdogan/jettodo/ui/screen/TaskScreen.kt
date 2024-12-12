package com.ibrahimcanerdogan.jettodo.ui.screen

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.ibrahimcanerdogan.jettodo.data.model.TaskPriority
import com.ibrahimcanerdogan.jettodo.data.model.ToDoModel
import com.ibrahimcanerdogan.jettodo.ui.screen.task.TaskAppBar
import com.ibrahimcanerdogan.jettodo.ui.screen.task.TaskContent
import com.ibrahimcanerdogan.jettodo.ui.viewmodel.BaseViewModel
import com.ibrahimcanerdogan.jettodo.utils.Action

@Composable
fun TaskScreen(
    selectedTask: ToDoModel?,
    viewModel: BaseViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    val title: String = viewModel.selectTitle
    val description: String = viewModel.selectDescription
    val taskPriority: TaskPriority = viewModel.selectTaskPriority

    val context = LocalContext.current

    BackHandler {
        navigateToListScreen(Action.NO_ACTION)
    }

    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = { action ->
                    if (action == Action.NO_ACTION) {
                        navigateToListScreen(action)
                    } else {
                        if (viewModel.validateFields()) {
                            navigateToListScreen(action)
                        } else {
                            displayToast(context = context)
                        }
                    }
                }
            )
        },
        content = { padding ->
            TaskContent(
                modifier = Modifier.padding(
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()
                ),
                title = title,
                onTitleChange = {
                    viewModel.updateTitle(it)
                },
                description = description,
                onDescriptionChange = {
                    viewModel.updateDescription(newDescription = it)
                },
                taskPriority = taskPriority,
                onPrioritySelected = {
                    viewModel.updatePriority(newPriority = it)
                }
            )
        }
    )
}

fun displayToast(context: Context) {
    Toast.makeText(
        context,
        "Fields Empty.",
        Toast.LENGTH_SHORT
    ).show()
}