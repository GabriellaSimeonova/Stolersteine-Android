package com.example.stolpersteine.activities

import StoneAPIManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.example.stolpersteine.R
import com.example.stolpersteine.models.MyViewModel
import com.example.stolpersteine.models.Stone
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun Homepage(onImageClicked: () -> Unit, myViewModel: MyViewModel) {
    val context = LocalContext.current
    val stoneAPIManager = remember { StoneAPIManager(context) }
    val stoneOfTheDayState = remember { mutableStateOf<Stone?>(null) }
//    val passedAwayOnThisDate = remember { mutableStateOf<List<Stone?>>(List<Stone?>) }
    val descriptionState = remember { mutableStateOf<String?>(null) }
    val closestToYou = listOf("2km", "2km", "3km")
    val popular = listOf("candles 210", "candles 200", "candles 109")
    val lessPop = listOf("candles 1", "candles 5", "candles 2")
    val passedOnThatDate = listOf("candles 56", "candles 50", "candles 40")

    LaunchedEffect(Unit) {
        val stoneOfTheDay = stoneAPIManager.getStoneOfTheDay()
        stoneOfTheDayState.value = stoneOfTheDay
        myViewModel.sharedObject.value = stoneOfTheDay
    }

//    LaunchedEffect(stoneOfTheDayState.value) {
//            stoneAPIManager.fetchDescriptionFromUrl(context,stoneOfTheDayState.value?.url!!) { description ->
//                descriptionState.value = description
//            }.toString()
//    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF1D0B5)
    ) {

    }
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.padding(10.dp))
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally)
        {
            val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            Text(
                "Today's date: $currentDate",
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF7F462C)
            )
            Text(text = "Stolpersteine of the Day", fontWeight = FontWeight.Bold, fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF7F462C))
        }
        if (stoneOfTheDayState.value != null) {
            StoneOfTheDay(stoneOfTheDayState.value!!, onImageClicked)
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            item {
                Spacer(modifier = Modifier.padding(10.dp))
                StoneElementsColumn(stoneElements = closestToYou, title = "Closest to you")
            }

            item {
                Spacer(modifier = Modifier.padding(10.dp))
                StoneElementsColumn(stoneElements = popular, title = "Popular")
            }

            item {
                Spacer(modifier = Modifier.padding(10.dp))
                StoneElementsColumn(stoneElements = lessPop, title = "Less popular")
            }

            item {
                Spacer(modifier = Modifier.padding(10.dp))
                StoneElementsColumn(stoneElements = passedOnThatDate, title = "Passed on this date")
            }
        }


    }

}
@Composable
fun StoneElement(text:String){
    Column(modifier = Modifier
        .size(100.dp, 120.dp)
        .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                , shape = RoundedCornerShape(25),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFFFFF)),
            onClick = {

            }
        ) {
            Image(
                painter = painterResource(id = R.mipmap.no_pic_foreground),
                contentDescription = "no_pic",
                contentScale = ContentScale.FillWidth
            )
        }
        Text(
            text = text,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            color = Color(0xFF7F462C)
        )
    }
}
@Composable
fun StoneElementsColumn(
    stoneElements: List<String>,
    title: String
) {
    Column(
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 5.dp)
        )
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth()
                .padding(20.dp, 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            stoneElements.forEach { text ->
                StoneElement(text = text)
            }
        }
    }
}



@Composable
fun StoneOfTheDay(stone: Stone, onImageClicked: () -> Unit) {
    var backgroundPic by remember { mutableStateOf(R.mipmap.candle2_foreground) }
    var isButtonVisible by remember { mutableStateOf(true) }
    var isTextVisible by remember { mutableStateOf(false) }

    Column {
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
                    painter = painterResource(id = backgroundPic),
                    contentDescription = "candle",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                        .clickable{onImageClicked()}
                )
            }

            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.End,
                ) {
                    Text(
                        text = stone.name,
                        color = Color(0xFFF2D0B5),
                        style = TextStyle(fontSize = 22.sp, fontFamily = FontFamily.SansSerif),
                        modifier = Modifier.padding(end = 30.dp, top = 15.dp),
                        textAlign = TextAlign.Center
                    )
                }
                if (isTextVisible) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "Date of birth: ${stone.dateOfBirth}\n" +
                                    "Date of death: ${stone.dateOfPassing}\n" +
                                    "Place of death: ${stone.placeOfPassing}\n" +
                                    "Cause of death: ${stone.reasonOfPassing}",
                            color = Color(0xFFF2D0B5),
                            style = TextStyle(fontSize = 12.sp, fontFamily = FontFamily.SansSerif),
                            modifier = Modifier.padding(end = 30.dp, top = 15.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(25.dp, 80.dp, 0.dp, 20.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    if (isButtonVisible) {
                        Button(
                            onClick = {
                                backgroundPic = R.mipmap.candle1_foreground
                                isButtonVisible = false
                                isTextVisible = true
                            },
                            modifier = Modifier.size(80.dp),
                            shape = CircleShape,
                            contentPadding = PaddingValues(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFFF1D0B5),
                                contentColor = Color(0xFF7F462C)
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
}


