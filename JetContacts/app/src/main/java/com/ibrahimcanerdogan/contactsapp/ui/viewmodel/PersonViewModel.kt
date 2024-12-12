package com.ibrahimcanerdogan.contactsapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ibrahimcanerdogan.contactsapp.data.model.Person
import com.ibrahimcanerdogan.contactsapp.data.repository.PersonRepository

class PersonViewModel : ViewModel() {
    private val repository = PersonRepository()
    var personList = MutableLiveData<List<Person>>()

    init {
        getAllPersonData()
        personList = repository.personList
    }

    fun getAllPersonData() {
        repository.getAllPerson()
    }

    fun searchPersonData(searchText: String) {
        repository.searchPerson(searchText)
    }

    fun registerPersonData(
        personName: String,
        personSurname: String,
        personPhone: String
    ) {
        repository.addPerson(personName, personSurname, personPhone)
    }

    fun updatePersonData(
        personID: String,
        personName: String,
        personSurname: String,
        personPhone: String
    ) {
       repository.updatePerson(personID, personName, personSurname, personPhone)
    }

    fun deletePersonData(personID: String) {
        repository.deletePerson(personID)
    }
}