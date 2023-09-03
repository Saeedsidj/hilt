package com.example.viewmodel

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactManager @Inject constructor() {
    private val _contacts : MutableStateFlow<List<Contact>> =MutableStateFlow(emptyList())
    val contact=_contacts.asStateFlow()

    fun addContact(contact: Contact){
        _contacts.value += contact
    }
    fun getContact(phone:String): Flow<Contact?> {
        return contact.map {
            it.find { contact ->  contact.phone==phone }
        }
    }

}