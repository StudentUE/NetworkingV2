package com.example.networkingv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.networkingv2.data.local.UserDatabase
import com.example.networkingv2.data.repository.UserRepository
import com.example.networkingv2.data.remote.RetrofitClient
import com.example.networkingv2.ui.screen.UserScreen
import com.example.networkingv2.ui.theme.NetworkingV2Theme
import com.example.networkingv2.ui.viewmodel.UserViewModel
import com.example.networkingv2.ui.viewmodel.UserViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val database = UserDatabase.getDatabase(this)


        val repository = UserRepository(database.userDao(), RetrofitClient.userApi)


        val viewModel = ViewModelProvider(this, UserViewModelFactory(repository))
            .get(UserViewModel::class.java)

        setContent {
            NetworkingV2Theme {
                UserScreen(viewModel)
            }
        }
    }
}
