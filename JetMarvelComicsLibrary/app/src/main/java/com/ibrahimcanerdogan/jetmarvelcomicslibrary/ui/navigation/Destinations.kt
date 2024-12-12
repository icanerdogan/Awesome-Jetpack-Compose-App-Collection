package com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.navigation

sealed class Destination(val route: String) {
    data object Library : Destination("library")
    data object CharacterList : Destination("characters")
    data object CharacterDetail : Destination("character/{characterId}") {
        fun createRoute(characterId: Int?) = "character/$characterId"
    }
}
