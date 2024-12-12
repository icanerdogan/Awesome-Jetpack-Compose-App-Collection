package com.ibrahimcanerdogan.jettodo.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ibrahimcanerdogan.jettodo.data.model.ToDoModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo_table ORDER BY todoID ASC")
    fun getAllTasksFromDatabase(): Flow<List<ToDoModel>>

    @Query("SELECT * FROM todo_table WHERE todoID=:taskId")
    fun getSelectedTaskFromDatabase(taskId: Int): Flow<ToDoModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTaskToDatabase(toDoTask: ToDoModel)

    @Update
    suspend fun updateTaskToDatabase(toDoTask: ToDoModel)

    @Delete
    suspend fun deleteTaskToDatabase(toDoTask: ToDoModel)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAllTasksFromDatabase()

    @Query("SELECT * FROM todo_table WHERE todoTitle LIKE :searchQuery OR todoDescription LIKE :searchQuery")
    fun searchTaskDatabase(searchQuery: String): Flow<List<ToDoModel>>

    @Query(
        """
        SELECT * FROM todo_table ORDER BY
    CASE
        WHEN todoPriority LIKE 'L%' THEN 1
        WHEN todoPriority LIKE 'M%' THEN 2
        WHEN todoPriority LIKE 'H%' THEN 3
    END
    """
    )
    fun sortByLowPriority(): Flow<List<ToDoModel>>

    @Query(
        """
        SELECT * FROM todo_table ORDER BY
    CASE
        WHEN todoPriority LIKE 'H%' THEN 1
        WHEN todoPriority LIKE 'M%' THEN 2
        WHEN todoPriority LIKE 'L%' THEN 3
    END
    """
    )
    fun sortByHighPriority(): Flow<List<ToDoModel>>
}