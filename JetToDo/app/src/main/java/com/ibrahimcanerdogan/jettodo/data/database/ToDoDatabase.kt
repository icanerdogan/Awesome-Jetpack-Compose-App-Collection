package com.ibrahimcanerdogan.jettodo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ibrahimcanerdogan.jettodo.data.model.ToDoModel

@Database(entities = [ToDoModel::class], version = 1, exportSchema = false)
abstract class ToDoDatabase: RoomDatabase() {

    abstract fun toDoDao(): ToDoDao

}