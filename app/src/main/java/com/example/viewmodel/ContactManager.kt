package com.example.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ContactManager @Inject constructor() {
    private val _contacts : MutableStateFlow<List<Contact>> =MutableStateFlow(emptyList())
    val contact=_contacts.asStateFlow()

    fun addContact(contact: Contact){
        _contacts.value += contact
    }

}