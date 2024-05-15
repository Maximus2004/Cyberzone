package com.example.cyberzone.data

import com.example.cyberzone.R
import com.example.cyberzone.ui.screens.boards.BoardGameScreen
import com.example.cyberzone.ui.screens.boards.BoardsSearchScreen
import com.example.cyberzone.ui.screens.home.NavigationItemContent

// TODO заменить pageType на route когда они появятся
val navigationItemContentList = listOf(
    NavigationItemContent(
        pageType = BoardsSearchScreen.route,
        icon = R.drawable.home,
        pageText = R.string.home
    ),
    NavigationItemContent(
        pageType = "BookScreen",
        icon = R.drawable.calendar,
        pageText = R.string.book
    ),
    NavigationItemContent(
        pageType = BoardGameScreen.route,
        icon = R.drawable.scanner,
        pageText = R.string.scanner
    ),
    NavigationItemContent(
        pageType = "ProfileScreen",
        icon = R.drawable.profile,
        pageText = R.string.profile
    )
)