package com.example.climate.ui.theme.screens.home

data class Post(
    val postId: String,
    val userId: String,
    val userName: String,
    val userProfession: String,
    val text: String,
    val imageUri: String? = null,
    val videoUri: String? = null
)