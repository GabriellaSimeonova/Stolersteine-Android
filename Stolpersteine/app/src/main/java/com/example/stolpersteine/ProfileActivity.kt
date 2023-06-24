package com.example.stolpersteine

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akexorcist.localizationactivity.core.LanguageSetting.setLanguage
import java.util.*





@Composable
fun ProfileScreen() {

    val context = LocalContext.current



    val isSheetOpen = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize()) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp), horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "My Profile",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF7F462C)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {



                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 30.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(modifier = Modifier
                        .size(120.dp),
                        shape = RoundedCornerShape(100),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF7F462C)),
                        onClick = { isSheetOpen.value = true }) {
                        Text(
                            text = "2",
                            fontSize = 60.sp,
                            color = Color(0xFFE2BA96)
                        )
                    }
                    Text(text = "Stones Visited", fontSize = 20.sp, color = Color(0xFF7F462C))
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 30.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(modifier = Modifier
                        .size(120.dp),
                        shape = RoundedCornerShape(100),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFD79B79)),
                        onClick = { isSheetOpen.value = true }) {
                        Text(
                            text = "2",
                            fontSize = 60.sp,
                            color = Color(0xFF7F462C)
                        )

                    }
                    Text(text = "Candles Lit", fontSize = 20.sp, color = Color(0xFF7F462C))
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 30.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(modifier = Modifier
                        .size(120.dp),
                        shape = RoundedCornerShape(100),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF3D1B6)),
                        onClick = { isSheetOpen.value = true }) {
                        Text(
                            text = "2",
                            fontSize = 60.sp,
                            color = Color(0xFF81452B)
                        )
                    }
                    Text(text = "Stones Saved", fontSize = 20.sp, color = Color(0xFF7F462C))
                }

            }

        }

        if (isSheetOpen.value) {
            Box(

                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x88000000))

            ) {
                // Content of the box on top of everything
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(600.dp)
                        .background(color = Color(0xFFFFF2E9))
                        .align(Alignment.BottomCenter)
                ) {
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Divider(
                            color = Color(0xFF7F462C),
                            thickness = 5.dp,
                            modifier = Modifier.padding(10.dp).width(80.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileActivity() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFfff2e9)
    ) {
        ProfileScreen()
    }
}

