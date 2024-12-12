package com.ibrahimcanerdogan.jettodo.domain.repository

import com.ibrahimcanerdogan.jettodo.data.model.TaskPriority
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow

@ViewModelScoped
interface DataStoreRepository {

    val readSortState: Flow<String>

    suspend fun writeSortState(taskPriority: TaskPriority)

}