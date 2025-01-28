package com.example.networkingv2.data.repository

import com.example.networkingv2.data.local.UserDao
import com.example.networkingv2.data.model.User
import com.example.networkingv2.data.remote.UserApi
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao, private val userApi: UserApi) {
    val users: Flow<List<User>> = userDao.getAllUsers()

    suspend fun fetchUsersFromApi() {
        try {
            val usersFromApi = userApi.getUsers()
            if (usersFromApi.isNotEmpty()) {
                userDao.deleteAllUsers()
                userDao.insertUsers(usersFromApi)
                println("Użytkownicy pobrani z API i zapisani!")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
        println("Wszyscy użytkownicy zostali usunięci z bazy danych")
    }

    suspend fun getUsersFromDatabase(): List<User> {
        return userDao.getAllUsersOnce()
    }
}
