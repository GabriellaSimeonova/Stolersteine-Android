package com.example.stolpersteine.navigation

import android.net.Uri
import com.google.gson.Gson
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.stolpersteine.*
import com.example.stolpersteine.activities.Homepage
import com.example.stolpersteine.activities.Splash
import com.example.stolpersteine.models.MyViewModel

@Composable
fun Navigation(navController: NavHostController) {

    val textToSpeech = remember {TextToSpeechActivity("")}
    val viewModel: MyViewModel = viewModel()

    NavHost(navController = navController, startDestination = "home_screen"){
        composable("splash_screen"){
            Splash(navController = navController)
        }
        composable("home_screen"){
            Homepage({navController.navigate("victim_profile")}, myViewModel = viewModel)
        }
        composable("profile_screen"){
            ProfileActivity();
        }
        composable("map_screen"){

        }
        composable("faq_screen"){
            FAQActivity()
        }

        composable("victim_profile"){
            VictimActivity(textToSpeechActivity = textToSpeech, viewModel);
        }



    }
}

