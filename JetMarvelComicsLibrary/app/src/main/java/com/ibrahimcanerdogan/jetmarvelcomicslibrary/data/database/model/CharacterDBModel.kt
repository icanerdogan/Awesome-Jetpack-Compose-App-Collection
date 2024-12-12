package com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.model.CharacterResult
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.utils.Constants.CHARACTER_TABLE

@Entity(tableName = CHARACTER_TABLE)
data class CharacterDBModel(
    @PrimaryKey(autoGenerate = true)
    val modelId: Int,
    val characterId: Int?,
    val characterName: String?,
    val characterThumbnail: String?,
    val characterComics: String?,
    val characterDescription: String?
) {
    companion object {
        fun fromCharacter(character: CharacterResult) =
            CharacterDBModel(
                modelId = 0,
                characterId = character.resultId,
                characterName = character.resultName,
                characterThumbnail = character.resultThumbnail?.path + "." + character.resultThumbnail?.extension,
                characterComics = character.resultComics?.items?.mapNotNull { it.name }?.comicsToString() ?: "no comics",
                characterDescription = character.resultDescription
            )
    }
}

fun List<String>.comicsToString() = this.joinToString(separator = ", ")