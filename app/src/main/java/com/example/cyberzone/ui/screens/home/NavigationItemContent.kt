package com.example.cyberzone.ui.screens.home

import androidx.annotation.StringRes

data class NavigationItemContent(
    val pageType: String,
    val icon: Int,
    @StringRes
    val pageText: Int
)
