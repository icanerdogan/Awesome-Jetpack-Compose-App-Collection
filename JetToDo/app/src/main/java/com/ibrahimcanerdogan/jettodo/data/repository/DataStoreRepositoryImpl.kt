package com.ibrahimcanerdogan.jettodo.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ibrahimcanerdogan.jettodo.data.model.TaskPriority
import com.ibrahimcanerdogan.jettodo.domain.repository.DataStoreRepository
import com.ibrahimcanerdogan.jettodo.utils.Constants.PREFERENCE_KEY
import com.ibrahimcanerdogan.jettodo.utils.Constants.PREFERENCE_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

class DataStoreRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : DataStoreRepository {

    private val dataStore = context.dataStore

    override val readSortState: Flow<String>
        get() = dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val sortState = preferences[sortKey] ?: TaskPriority.NONE.name
                sortState
            }

    override suspend fun writeSortState(taskPriority: TaskPriority) {
        dataStore.edit { preference ->
            preference[sortKey] = taskPriority.name
        }    }

    companion object PreferenceKeys {
        val sortKey = stringPreferencesKey(name = PREFERENCE_KEY)
    }
}