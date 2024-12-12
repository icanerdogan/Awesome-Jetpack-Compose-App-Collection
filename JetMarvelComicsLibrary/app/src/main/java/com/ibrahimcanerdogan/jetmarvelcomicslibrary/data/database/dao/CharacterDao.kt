package com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.database.model.CharacterDBModel
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.utils.Constants.CHARACTER_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM $CHARACTER_TABLE ORDER BY modelId ASC")
    fun getCharactersDatabase(): Flow<List<CharacterDBModel>>

    @Query("SELECT * FROM $CHARACTER_TABLE WHERE modelId = :characterId")
    fun getSingleCharacterDatabase(characterId: Int): Flow<CharacterDBModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCharacterDatabase(character: CharacterDBModel)

    @Update
    fun updateCharacterDatabase(character: CharacterDBModel)

    @Delete
    fun deleteCharacterDatabase(character: CharacterDBModel)

}