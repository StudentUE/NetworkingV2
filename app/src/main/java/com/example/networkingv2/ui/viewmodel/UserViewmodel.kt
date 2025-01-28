package com.example.networkingv2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkingv2.data.model.User
import com.example.networkingv2.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    init {
        viewModelScope.launch {
            loadUsers()
        }
    }

    fun fetchUsers() {
        viewModelScope.launch {
            repository.fetchUsersFromApi()
        }
    }

    fun loadUsersFromDatabase() {
        viewModelScope.launch {
            _users.value = repository.getUsersFromDatabase()
        }
    }

    fun clearDatabase() {
        viewModelScope.launch {
            repository.deleteAllUsers()
            _users.value = emptyList()
        }
    }


    private suspend fun loadUsers() {
        _users.value = repository.getUsersFromDatabase()
    }
}
