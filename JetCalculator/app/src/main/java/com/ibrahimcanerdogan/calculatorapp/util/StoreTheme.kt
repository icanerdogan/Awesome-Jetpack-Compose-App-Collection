package com.ibrahimcanerdogan.calculatorapp.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class StoreTheme(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Theme")
        val THEME_KEY = booleanPreferencesKey("theme_key")
    }

    val getTheme: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[THEME_KEY] ?: false
        }

    suspend fun saveTheme(isDarkTheme: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkTheme
        }
    }

    fun getThemeSynchronously() : Boolean {
        return runBlocking {
            context.dataStore.data
                .map { preferences -> preferences[THEME_KEY] ?: false }
                .first()
        }
    }

}