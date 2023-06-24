package com.example.stolpersteine.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun Navbar(
    items: List<NavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (NavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color(0xFFF7F7FF),
        elevation = 5.dp) {
        items.forEach{
                item->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(selected = selected,
                onClick = {onItemClick(item)},
                selectedContentColor = Color(0xFF7F462c),
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(painter = item.icon, contentDescription = "${item.name} icon")
                        if (selected){
                            Text(text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp)
                        }
                    }
                }

            )
        }
    }
}

@Composable
fun ButtonIcon(
    icon: Painter,
    title: String,
    route: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column {
        Box(
            modifier = Modifier
                .background(
                    if (isSelected) {
                        Brush.verticalGradient(
                            listOf(Color(0xFF9B4AFF), Color(0xFF9a348e)),
                            startY = 0f,
                            endY = 50f
                        )
                    } else {
                        Brush.verticalGradient(
                            listOf(Color.Transparent, Color.Transparent),
                            startY = 0f,
                            endY = 50f
                        )
                    },
                    shape = RoundedCornerShape(15.dp)
                )
                .clickable { onClick() },
            contentAlignment = Alignment.CenterStart
        ) {
            Icon(painter = icon, contentDescription = "$title icon")
            Text(text = title, fontWeight = FontWeight.Light)
        }
    }
}