package com.example.viewmodel

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(
    viewModel: ContactListViewModel = hiltViewModel(),
   onItemClick:( phone:String )->Unit
) {
    var textName by remember { mutableStateOf("") }
    var textPhone by remember { mutableStateOf("") }
    val contacts by viewModel.contacts.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = textName,
            onValueChange = { textName = it },
            label = { Text(text = "N A M E") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "") })
        OutlinedTextField(
            value = textPhone,
            onValueChange = { textPhone = it },
            label = { Text(text = "P H O N E") },
            leadingIcon = { Icon(Icons.Default.Call, contentDescription = "") })
        Button(
            onClick = {
                viewModel.addContact(textName, textPhone)
            },
            enabled = textName.isNotEmpty() && textPhone.isNotEmpty()
        ) {
            Text(text = "S U B M I T")
        }
        LazyColumn {
            items(contacts) {
                Column(
                    modifier = Modifier.clickable {
                        onItemClick(it.phone)
                    }
                ) {
                    Text(text = it.name)
                    Text(text = it.phone)
                }
            }
        }
    }
}