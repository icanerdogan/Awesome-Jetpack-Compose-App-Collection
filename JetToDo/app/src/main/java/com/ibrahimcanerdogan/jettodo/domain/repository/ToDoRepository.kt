package com.ibrahimcanerdogan.jettodo.domain.repository

import com.ibrahimcanerdogan.jettodo.data.model.ToDoModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow

@ViewModelScoped
interface ToDoRepository {

    val getAllTasks: Flow<List<ToDoModel>>
    val sortByLowPriority: Flow<List<ToDoModel>>
    val sortByHighPriority: Flow<List<ToDoModel>>

    suspend fun addTask(toDoModel: ToDoModel)

    suspend fun updateTask(toDoModel: ToDoModel)

    suspend fun deleteTask(toDoModel: ToDoModel)

    suspend fun deleteAllTasks()

    fun searchTask(searchQuery: String): Flow<List<ToDoModel>>

    fun selectTask(taskID: Int): Flow<ToDoModel>
}