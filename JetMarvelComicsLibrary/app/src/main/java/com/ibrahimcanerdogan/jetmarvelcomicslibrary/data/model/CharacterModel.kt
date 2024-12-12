package com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("code")
    val responseCode: String?,
    @SerializedName("status")
    val responseStatus: String?,
    @SerializedName("attributionText")
    val responseAttributionText: String?,
    @SerializedName("data")
    val responseData: CharactersData?
)

data class CharactersData(
    @SerializedName("total")
    val total: Int?,
    @SerializedName("results")
    val results: List<CharacterResult>?
)

data class CharacterResult(
    @SerializedName("id")
    val resultId: Int?,
    @SerializedName("name")
    val resultName: String?,
    @SerializedName("description")
    val resultDescription: String?,
    @SerializedName("resourceURI")
    val resultResourceURI: String?,
    @SerializedName("urls")
    val resultUrls: List<CharacterResultUrl>?,
    @SerializedName("thumbnail")
    val resultThumbnail: CharacterThumbnail?,
    @SerializedName("comics")
    val resultComics: CharacterComics?
)

data class CharacterResultUrl(
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?
)

data class CharacterThumbnail(
    @SerializedName("path")
    val path: String?,
    @SerializedName("extension")
    val extension: String?
)

data class CharacterComics(
    @SerializedName("items")
    val items: List<CharacterComicsItems>?
)

data class CharacterComicsItems(
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("name")
    val name: String?
)