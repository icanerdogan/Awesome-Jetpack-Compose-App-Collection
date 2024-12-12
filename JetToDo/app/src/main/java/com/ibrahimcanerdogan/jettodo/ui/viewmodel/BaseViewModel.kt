package com.ibrahimcanerdogan.jettodo.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimcanerdogan.jettodo.data.model.TaskPriority
import com.ibrahimcanerdogan.jettodo.data.model.ToDoModel
import com.ibrahimcanerdogan.jettodo.domain.repository.DataStoreRepository
import com.ibrahimcanerdogan.jettodo.domain.repository.ToDoRepository
import com.ibrahimcanerdogan.jettodo.utils.Action
import com.ibrahimcanerdogan.jettodo.utils.Constants.MAX_TITLE_LENGTH
import com.ibrahimcanerdogan.jettodo.utils.SearchState
import com.ibrahimcanerdogan.jettodo.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(
    private val repository: ToDoRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    var action by mutableStateOf(Action.NO_ACTION)

    var selectID by mutableIntStateOf(0)
    var selectTitle by mutableStateOf("")
    var selectDescription by mutableStateOf("")
    var selectTaskPriority by mutableStateOf(TaskPriority.LOW)

    var searchState by mutableStateOf(SearchState.CLOSED)
    var searchTextState by mutableStateOf("")

    private val _allTasks = MutableStateFlow<State<List<ToDoModel>>>(State.Idle)
    val allTasks: StateFlow<State<List<ToDoModel>>> = _allTasks

    private val _searchedTasks = MutableStateFlow<State<List<ToDoModel>>>(State.Idle)
    val searchedTasks: StateFlow<State<List<ToDoModel>>> = _searchedTasks

    private val _sortState = MutableStateFlow<State<TaskPriority>>(State.Idle)
    val sortState: StateFlow<State<TaskPriority>> = _sortState

    private val _selectedTask: MutableStateFlow<ToDoModel?> = MutableStateFlow(null)
    val selectedTask: StateFlow<ToDoModel?> = _selectedTask

    init {
        getAllTasks()
        readSortState()
    }

    val lowPriorityTasks: StateFlow<List<ToDoModel>> =
        repository.sortByLowPriority.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    val highPriorityTasks: StateFlow<List<ToDoModel>> =
        repository.sortByHighPriority.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    fun getSelectedTask(taskId: Int) {
        viewModelScope.launch {
            repository.selectTask(taskID = taskId).collect { task ->
                _selectedTask.value = task
            }
        }
    }

    fun searchDatabase(searchQuery: String) {
        _searchedTasks.value = State.Loading
        try {
            viewModelScope.launch {
                repository.searchTask(searchQuery = "%$searchQuery%")
                    .collect { searchedTasks ->
                        _searchedTasks.value = State.Success(searchedTasks)
                    }
            }
        } catch (e: Exception) {
            _searchedTasks.value = State.Error(e)
        }
        searchState = SearchState.TRIGGERED
    }

    private fun getAllTasks() {
        _allTasks.value = State.Loading
        try {
            viewModelScope.launch {
                repository.getAllTasks.collect {
                    _allTasks.value = State.Success(it)
                }
            }
        } catch (e: Exception) {
            _allTasks.value = State.Error(e)
        }
    }

    private fun readSortState() {
        _sortState.value = State.Loading
        try {
            viewModelScope.launch {
                dataStoreRepository.readSortState
                    .map { TaskPriority.valueOf(it) }
                    .collect {
                        _sortState.value = State.Success(it)
                    }
            }
        } catch (e: Exception) {
            _sortState.value = State.Error(e)
        }
    }

    fun persistSortState(priority: TaskPriority) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.writeSortState(taskPriority = priority)
        }
    }

    fun handleDatabaseActions(action: Action) {
        when (action) {
            Action.ADD -> {
                addTask()
                updateAction(Action.NO_ACTION)
            }
            Action.UPDATE -> {
                updateTask()
            }
            Action.DELETE -> {
                deleteTask()
            }
            Action.DELETE_ALL -> {
                deleteAllTasks()
            }
            Action.UNDO -> {
                addTask()
                updateAction(Action.NO_ACTION)
            }
            else -> {

            }
        }
    }

    private fun addTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoModel(
                todoTitle = selectTitle,
                todoDescription = selectDescription,
                todoPriority = selectTaskPriority
            )
            repository.addTask(toDoModel = toDoTask)
        }
        searchState = SearchState.CLOSED
    }

    private fun updateTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoModel(
                todoID = selectID,
                todoTitle = selectTitle,
                todoDescription = selectDescription,
                todoPriority = selectTaskPriority
            )
            repository.updateTask(toDoModel = toDoTask)
        }
    }

    private fun deleteTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoModel(
                todoID = selectID,
                todoTitle = selectTitle,
                todoDescription = selectDescription,
                todoPriority = selectTaskPriority
            )
            repository.deleteTask(toDoModel = toDoTask)
        }
    }

    private fun deleteAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTasks()
        }
    }


    fun updateTaskFields(selectedTask: ToDoModel?) {
        if (selectedTask != null) {
            selectID = selectedTask.todoID
            selectTitle = selectedTask.todoTitle
            selectDescription = selectedTask.todoDescription
            selectTaskPriority = selectedTask.todoPriority
        } else {
            selectID = 0
            selectTitle = ""
            selectDescription = ""
            selectTaskPriority = TaskPriority.LOW
        }
    }

    fun updateTitle(newTitle: String) = run {
        if (newTitle.length < MAX_TITLE_LENGTH) {
            selectTitle = newTitle
        }
    }

    fun updateDescription(newDescription: String) = run { selectDescription = newDescription }

    fun updatePriority(newPriority: TaskPriority) = run { selectTaskPriority = newPriority }

    fun updateAction(newAction: Action) = run { action = newAction }

    fun updateAppBarState(newState: SearchState) = run { searchState = newState }

    fun updateSearchText(newText: String) = run { searchTextState = newText }

    fun validateFields(): Boolean {
        return selectTitle.isNotEmpty() && selectDescription.isNotEmpty()
    }

}