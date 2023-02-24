package com.example.testtask.content_screen.profile_screen

import android.net.Uri

data class ProfileListItem(
    val icon: Int,
    val title: String,
    val withArrow: Boolean,
    val description: String = ""
)
