package com.example.climate.ui.theme.screens.library

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.climate.R

@Composable
fun Repo(){
    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = "BACK",
        modifier = Modifier.fillMaxSize())
    Box (){

    }
}