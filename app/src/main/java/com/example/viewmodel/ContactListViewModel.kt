package com.example.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ContactListViewModel @Inject constructor(
    private val contactManager: ContactManager
) :ViewModel(){

    val contacts=contactManager.contact

    fun addContact(name:String,phone:String){
        contactManager.addContact(Contact(name,phone))
    }

}