package com.ibrahimcanerdogan.jettodo.data.repository

import com.ibrahimcanerdogan.jettodo.data.database.ToDoDao
import com.ibrahimcanerdogan.jettodo.data.model.ToDoModel
import com.ibrahimcanerdogan.jettodo.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val toDoDao: ToDoDao
) : ToDoRepository {

    override val getAllTasks: Flow<List<ToDoModel>>
        get() = toDoDao.getAllTasksFromDatabase()
    override val sortByLowPriority: Flow<List<ToDoModel>>
        get() = toDoDao.sortByLowPriority()
    override val sortByHighPriority: Flow<List<ToDoModel>>
        get() = toDoDao.sortByHighPriority()

    override suspend fun addTask(toDoModel: ToDoModel) {
        toDoDao.addTaskToDatabase(toDoModel)
    }

    override suspend fun updateTask(toDoModel: ToDoModel) {
        toDoDao.updateTaskToDatabase(toDoModel)
    }

    override suspend fun deleteTask(toDoModel: ToDoModel) {
        toDoDao.deleteTaskToDatabase(toDoModel)
    }

    override suspend fun deleteAllTasks() {
        toDoDao.deleteAllTasksFromDatabase()
    }

    override fun searchTask(searchQuery: String): Flow<List<ToDoModel>> {
        return toDoDao.searchTaskDatabase(searchQuery)
    }

    override fun selectTask(taskID: Int): Flow<ToDoModel> {
        return toDoDao.getSelectedTaskFromDatabase(taskID)
    }
}