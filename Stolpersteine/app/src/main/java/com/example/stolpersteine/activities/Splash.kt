package com.example.stolpersteine.activities

import android.media.Image
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.navigation.NavController
import com.example.stolpersteine.R
import kotlinx.coroutines.delay

@Composable
fun Splash(navController: NavController){
    val scale = remember{
        Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(targetValue = 2f, animationSpec = tween(durationMillis = 550, easing = {
            OvershootInterpolator(2f).getInterpolation(it)
        }))
        delay(2500L)
        navController.navigate("home_screen")
    }
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.mipmap.no_pic_foreground), contentDescription = "Logo", modifier = Modifier.scale(scale = scale.value))
        Text(text = "Uncover the Untold Stories")
    }
}
