package com.ibrahimcanerdogan.contactsapp.data.model

import java.util.UUID

data class Person(
    var personID: String = UUID.randomUUID().toString(),
    var personName: String? = null,
    var personSurname: String? = null,
    var personPhoneNumber: String? = null
)