package com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.database.dao.CharacterDao
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.database.dao.NoteDao
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.database.model.CharacterDBModel
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.database.model.NoteDBModel

@Database(entities = [CharacterDBModel::class, NoteDBModel::class], version = 1, exportSchema = false)
abstract class MarvelDatabase: RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    abstract fun noteDao(): NoteDao

}