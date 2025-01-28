package com.example.networkingv2.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.networkingv2.data.model.User
import com.example.networkingv2.ui.viewmodel.UserViewModel

@Composable
fun UserScreen(viewModel: UserViewModel) {
    val users by viewModel.users.collectAsState()
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Button(
            onClick = {
                isLoading = true
                viewModel.fetchUsers()
            }
        ) {
            Text("Pobierz użytkowników z API do bazy")
        }

        Spacer(modifier = Modifier.height(8.dp))


        Button(
            onClick = {
                isLoading = true
                viewModel.loadUsersFromDatabase()
            }
        ) {
            Text("Pobierz z bazy do wyświetlenia")
        }

        Spacer(modifier = Modifier.height(8.dp))


        Button(
            onClick = {
                isLoading = true
                viewModel.clearDatabase()
            },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Text("Wyczyść bazę")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when {
            isLoading && users.isEmpty() -> {
                Text("Ładowanie danych...", modifier = Modifier.padding(8.dp))
            }
            users.isEmpty() -> {
                Text("Brak danych do wyświetlenia")
            }
            else -> {
                LazyColumn {
                    items(users) { user ->
                        UserItem(user)
                    }
                }
                isLoading = false
            }
        }
    }
}

@Composable
fun UserItem(user: User) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = " ${user.name}", style = MaterialTheme.typography.titleMedium)
            Text(text = " ${user.username}", style = MaterialTheme.typography.bodySmall)
            Text(text = "📧 ${user.email}", style = MaterialTheme.typography.bodySmall)
            Text(text = "📞 ${user.phone}", style = MaterialTheme.typography.bodySmall)
            Text(text = "🌍 ${user.website}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
