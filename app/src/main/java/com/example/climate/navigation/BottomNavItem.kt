package com.example.climate.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val title: String, val icon: ImageVector, val route: String) {
    object Home : BottomNavItem("Home", Icons.Filled.Home, ROUTE_HOME)
    object MyNetwork : BottomNavItem("My Network", Icons.Filled.Person, ROUTE_LIBRARY)
    object Jobs : BottomNavItem("Jobs", Icons.Filled.Add, ROUTE_LIBRARY)
    object Messaging : BottomNavItem("Messaging", Icons.Filled.Info, "messages")
    // Add more items as needed
}
