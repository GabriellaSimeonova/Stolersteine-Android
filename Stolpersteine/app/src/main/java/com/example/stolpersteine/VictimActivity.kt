package com.example.stolpersteine

import android.R.attr.top
import android.R.attr.y
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.stolpersteine.models.MyViewModel
import com.example.stolpersteine.models.Stone
import com.example.stolpersteine.ui.theme.StolpersteineTheme


@Composable
fun VictimActivity(textToSpeechActivity: TextToSpeechActivity, myViewModel: MyViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFfff2e9)
    ) {
        GetInfo(textToSpeechActivity = textToSpeechActivity, myViewModel = myViewModel)
    }

}





@Composable
fun GetInfo(textToSpeechActivity: TextToSpeechActivity, myViewModel: MyViewModel) {

    val context = LocalContext.current

    var buttonId by remember { mutableStateOf(R.mipmap.candle2_foreground) }
    var isButtonVisible by remember { mutableStateOf(true) }
    val scrollState = rememberScrollState()
    val sharedObject = myViewModel.sharedObject.value

    val text = "Tell a story about ${sharedObject!!.name} in first person with the provided information and don't talk about their feelings, just facts: They were born on ${sharedObject!!.dateOfBirth} in ${sharedObject!!.city}, they sadly passed away on ${sharedObject!!.dateOfPassing} in ${sharedObject!!.placeOfPassing} due to ${sharedObject!!.reasonOfPassing}. *Translate the reason for passing away from dutch to english"
    val result = remember { mutableStateOf("") }



    DisposableEffect(Unit) {
        tellStory(text) { response ->
            result.value = response
        }



        onDispose {
        }
    }




    @Composable
    fun candle() {
        Spacer(modifier = Modifier.padding(20.dp))
        Box(
            modifier = Modifier
                .wrapContentSize(align = Alignment.Center)
        ) {
            Box(
                modifier = Modifier
                    .height(250.dp)
                    .width(350.dp)
                    .clip(RoundedCornerShape(15))
                    .fillMaxWidth()
                    .align(Alignment.Center)
            ) {
                Image(
                    painter = painterResource(id = buttonId),
                    contentDescription = "candle",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),

                    )
            }

            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.End,
//                verticalAlignment = Alignment.End
                ) {
                    Text(
                        text = sharedObject!!.name,
                        color = Color(0xFFF2D0B5),
                        style = TextStyle(fontSize = 25.sp, fontFamily = FontFamily.SansSerif),
                        modifier = Modifier.padding(end = 50.dp, top = 15.dp),
                        textAlign = TextAlign.Center
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    if (isButtonVisible) {
                        Button(
                            onClick = {
                                buttonId = R.mipmap.candle1_foreground; isButtonVisible = false
                            },
                            modifier = Modifier.size(80.dp),

                            shape = CircleShape,
                            contentPadding = PaddingValues(8.dp),
                            colors = androidx.compose.material.ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFFF1D0B5),
                                contentColor = Color(0xFF0FF7F462C)
                            )
                        ) {
                            Text(
                                text = "Light a candle",
                                modifier = Modifier.wrapContentSize(),
                                color = Color(0xFF7F462C)
                            )
                        }
                    }
                }


            }


        }
    }


    @Composable
    fun details() {
        Spacer(modifier = Modifier.padding(20.dp))
        Row(modifier = Modifier.fillMaxWidth()) {

            Text(
                text = "Date of birth: ${sharedObject!!.dateOfBirth}\nDate of death: ${sharedObject!!.dateOfPassing}\nPlace of death: ${sharedObject!!.placeOfPassing}\nCause of death: ${sharedObject!!.reasonOfPassing}",
                color = Color(0xFF7F462C),
                modifier = Modifier.padding(horizontal = 20.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )

            Button(
                onClick = {
                    textToSpeechActivity.textToSpeech(context, result)
                },
                modifier = Modifier.size(80.dp),

                shape = CircleShape,
                contentPadding = PaddingValues(8.dp),
                colors = androidx.compose.material.ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFF1D0B5),
                    contentColor = Color(0xFF7F462C)
                )
            ) {
                Text(
                    text = "Play story",
                    modifier = Modifier.wrapContentSize(),
                    color = Color(0xFF7F462C)
                )
            }
        }
    }
    LazyColumn() {
  

        item { candle() }


        item { details() }


        item {

            Text(
                text = result.value,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 20.dp),
                textAlign = TextAlign.Justify,
                color = Color(0xFF7F462C),
                fontSize = 15.sp
            )
        }
    }





//
//
//    Column() {
//        Spacer(modifier = Modifier.padding(20.dp))
//
//        Box(
//            modifier = Modifier
//                .wrapContentSize(align = Alignment.Center)
//        ) {
//            Box(
//                modifier = Modifier
//                    .height(250.dp)
//                    .width(350.dp)
//                    .clip(RoundedCornerShape(15))
//                    .fillMaxWidth()
//                    .align(Alignment.Center)
//            ) {
//                Image(
//                    painter = painterResource(id = buttonId),
//                    contentDescription = "candle",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .align(Alignment.Center),
//
//                    )
//            }
//
//            Column() {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp),
//                    horizontalArrangement = Arrangement.End,
////                verticalAlignment = Alignment.End
//                ) {
//                    Text(
//                        text = sharedObject!!.name,
//                        color = Color(0xFFF2D0B5),
//                        style = TextStyle(fontSize = 25.sp, fontFamily = FontFamily.SansSerif),
//                        modifier = Modifier.padding(end = 50.dp, top = 15.dp),
//                        textAlign = TextAlign.Center
//                    )
//                }
//
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp),
//                    horizontalArrangement = Arrangement.Center,
//                ) {
//                    if (isButtonVisible) {
//                        Button(
//                            onClick = {
//                                buttonId = R.mipmap.candle1_foreground; isButtonVisible = false
//                            },
//                            modifier = Modifier.size(80.dp),
//
//                            shape = CircleShape,
//                            contentPadding = PaddingValues(8.dp),
//                            colors = androidx.compose.material.ButtonDefaults.buttonColors(
//                                backgroundColor = Color(0xFFF1D0B5),
//                                contentColor = Color(0xFF0FF7F462C)
//                            )
//                        ) {
//                            Text(
//                                text = "Light a candle",
//                                modifier = Modifier.wrapContentSize(),
//                                color = Color(0xFF7F462C)
//                            )
//                        }
//                    }
//                }
//
//
//            }
//
//
//        }
//        Spacer(modifier = Modifier.padding(20.dp))
//
//        Row(modifier = Modifier.fillMaxWidth()) {
//
//            Text(
//                text = "Date of birth: ${sharedObject!!.dateOfBirth}\nDate of death: ${sharedObject!!.dateOfPassing}\nPlace of death: ${sharedObject!!.placeOfPassing}\nCause of death: ${sharedObject!!.reasonOfPassing}",
//                color = Color(0xFF7F462C),
//                modifier = Modifier.padding(horizontal = 20.dp),
//                fontWeight = FontWeight.Bold,
//                fontSize = 15.sp
//            )
//
//            Button(
//                onClick = {
//                      textToSpeechActivity.textToSpeech(context)
//                },
//                modifier = Modifier.size(80.dp),
//
//                shape = CircleShape,
//                contentPadding = PaddingValues(8.dp),
//                colors = androidx.compose.material.ButtonDefaults.buttonColors(
//                    backgroundColor = Color(0xFFF1D0B5),
//                    contentColor = Color(0xFF7F462C)
//                )
//            ) {
//                Text(
//                    text = "Play story",
//                    modifier = Modifier.wrapContentSize(),
//                    color = Color(0xFF7F462C)
//                )
//            }
//        }
//
//        Spacer(modifier = Modifier.padding(10.dp))
//        Text(
//            text = result.value,
//            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
//            textAlign = TextAlign.Justify,
//            color = Color(0xFF7F462C),
//            fontSize = 15.sp
//        )
//    }




}






