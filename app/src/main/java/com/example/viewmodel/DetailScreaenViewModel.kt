package com.example.viewmodel

import androidx.compose.runtime.internal.illegalDecoyCallException
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class DetailScreaenViewModel @Inject constructor(
    private val contactManager: ContactManager,
    savedStateHandle: SavedStateHandle
):ViewModel() {
    @OptIn(ExperimentalCoroutinesApi::class)
    val phone=savedStateHandle.getStateFlow<String>("phone","Null")
        .flatMapLatest { contactManager.getContact(it) }
}