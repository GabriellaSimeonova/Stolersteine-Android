package com.example.stolpersteine

import StoneAPIManager
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.example.stolpersteine.navigation.NavItem
import com.example.stolpersteine.navigation.Navbar
import com.example.stolpersteine.navigation.Navigation
import java.io.File
import java.util.concurrent.ExecutorService

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {

        //here we init the stone manager and update the stones.json file :D
        val stoneAPIManager = StoneAPIManager(applicationContext)
        stoneAPIManager.updateStonesFromAPI()

        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            Scaffold(
                bottomBar = {
                    Navbar(
                        items = listOf(
                            NavItem(
                                name = "Home",
                                route = "home_screen",
                                icon = painterResource(id = R.mipmap.home_foreground)
                            ),
                            NavItem(
                                name = "Map",
                                route = "map_screen",
                                icon = painterResource(id = R.mipmap.map_foreground)
                            ),
                            NavItem(
                                name = "faq",
                                route = "faq_screen",
                                icon = painterResource(id = R.mipmap.faq_foreground)
                            ),
                            NavItem(
                                name = "Profile",
                                route = "profile_screen",
                                icon = painterResource(id = R.mipmap.profile_foreground)
                            ),
                        ), navController = navController, onItemClick = {
                            navController.navigate(it.route);
                        }
                    )
                }
            ) {
                Navigation(navController = navController);
            }
        }
    }
}