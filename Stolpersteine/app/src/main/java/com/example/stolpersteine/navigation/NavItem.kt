package com.example.stolpersteine.navigation

import androidx.compose.ui.graphics.painter.Painter

data class NavItem(
    val name: String,
    val route: String,
    val icon: Painter,
)