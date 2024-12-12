package com.ibrahimcanerdogan.jettodo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ibrahimcanerdogan.jettodo.utils.Constants.DATABASE_TABLE

@Entity(tableName = DATABASE_TABLE)
data class ToDoModel(
    @PrimaryKey(autoGenerate = true)
    val todoID: Int = 0,
    val todoTitle: String,
    val todoDescription: String,
    val todoPriority: TaskPriority
)