package com.example.climate.ui.theme.screens.communities

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.climate.R

@Composable
fun MyCommunities(navController: NavController){
    Image(painter = painterResource(id = R.drawable.background), contentDescription = "BACK")
}