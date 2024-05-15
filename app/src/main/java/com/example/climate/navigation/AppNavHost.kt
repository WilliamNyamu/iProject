package com.example.climate.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.climate.ui.theme.screens.communities.MyCommunities
import com.example.climate.ui.theme.screens.home.HomeScreen
import com.example.climate.ui.theme.screens.home.PostScreen
import com.example.climate.ui.theme.screens.login.LoginScreen
import com.example.climate.ui.theme.screens.register.RegisterScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String= ROUTE_POST
) {
    NavHost(
        navController = navController,
        modifier = Modifier,
        startDestination = startDestination
    ) {

        composable(ROUTE_LOGIN) {
            val usernameState = remember { mutableStateOf("") } // Initialize username state
            val passwordState = remember { mutableStateOf("") } // Initialize password state
            LoginScreen(navController)


        }
//        composable(ROUTE_HOME) { HomeScreen}
        composable(ROUTE_REGISTER) { RegisterScreen(navController) }
        composable(ROUTE_COMMUNITIES) { MyCommunities(navController) }
        composable(ROUTE_HOME) {
            HomeScreen(
                navController = navController,
                posts = emptyList(),
                onPostClick = {}
            )
        }
        composable(ROUTE_LIBRARY) {
            MyCommunities(navController)
        }
//        composable(ROUTE_PROFILE) {
//            val navController = LocalNavController.current
//            profileScreen(navController)
//        }
        composable(ROUTE_NOTIFICATION) {
            MyCommunities(navController)
        }
        composable(ROUTE_POST){ PostScreen(onPost = { text, imageUri ->}, navController)}
    }
}
