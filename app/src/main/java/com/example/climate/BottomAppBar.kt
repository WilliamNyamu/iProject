package com.example.climate

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.climate.navigation.BottomNavItem
import com.example.climate.ui.theme.screens.communities.MyCommunities
import com.example.climate.ui.theme.screens.home.HomeScreen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun YourActivity() {
    val navController = rememberNavController()
    val currentRoute = remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            Surface(
                color = MaterialTheme.colorScheme.primary, // Set bottom bar color
                contentColor = MaterialTheme.colorScheme.onPrimary // Set content color
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp), // Add some padding
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val items = listOf(
                        BottomNavItem.Home,
                        BottomNavItem.MyNetwork,
                        BottomNavItem.Jobs,
                        BottomNavItem.Messaging,
                    )
                    items.forEach { item ->
                        IconButton(
                            onClick = {
                                currentRoute.value = item.route
                                navController.navigate(item.route)
                            },
                        ) {
                            Icon(item.icon, contentDescription = item.title)
//                            if (currentRoute.value == item.route) {
//                                Text(item.title) // Add text label only for selected item
//                            }
                        }
                    }
                }
            }
        }
    ) {
        NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
            composable(BottomNavItem.Home.route) { HomeScreen(rememberNavController(), emptyList(),{}) }
            composable(BottomNavItem.MyNetwork.route) { MyCommunities(rememberNavController()) }
            // Add composables for Jobs and Messaging screens
        }
    }
}
