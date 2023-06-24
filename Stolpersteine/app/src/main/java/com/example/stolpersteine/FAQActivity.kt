package com.example.stolpersteine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stolpersteine.ui.theme.StolpersteineTheme

@Composable
fun FAQScreen() {
    val faqItems = listOf(
        FAQItem(
            "Question 1",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse faucibus sapien ac euismod venenatis. Nullam at molestie leo. Fusce et sollicitudin nisl. Integer auctor augue non sem porta semper. Duis molestie feugiat augue, eget feugiat massa pellentesque at. Maecenas vestibulum mi quis bibendum finibus. Nunc viverra, nunc eu vestibulum vehicula, odio nisl pellentesque quam, quis facilisis velit ipsum nec enim. Sed venenatis urna quis orci dictum fermentum. Curabitur eu hendrerit turpis, sit amet tristique massa. Integer et nulla a elit viverra dignissim nec non velit. Duis rhoncus quam id velit suscipit rutrum. Praesent scelerisque, lacus eget egestas mattis, mauris ante imperdiet libero, vitae egestas ligula tellus non orci. Etiam ultrices gravida enim ac fringilla."
        ),
        FAQItem(
            "Question 2",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse faucibus sapien ac euismod venenatis. Nullam at molestie leo. Fusce et sollicitudin nisl. Integer auctor augue non sem porta semper. Duis molestie feugiat augue, eget feugiat massa pellentesque at. Maecenas vestibulum mi quis bibendum finibus. Nunc viverra, nunc eu vestibulum vehicula, odio nisl pellentesque quam, quis facilisis velit ipsum nec enim. Sed venenatis urna quis orci dictum fermentum. Curabitur eu hendrerit turpis, sit amet tristique massa. Integer et nulla a elit viverra dignissim nec non velit. Duis rhoncus quam id velit suscipit rutrum. Praesent scelerisque, lacus eget egestas mattis, mauris ante imperdiet libero, vitae egestas ligula tellus non orci. Etiam ultrices gravida enim ac fringilla."
        ),
        FAQItem(
            "Question 3",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse faucibus sapien ac euismod venenatis. Nullam at molestie leo. Fusce et sollicitudin nisl. Integer auctor augue non sem porta semper. Duis molestie feugiat augue, eget feugiat massa pellentesque at. Maecenas vestibulum mi quis bibendum finibus. Nunc viverra, nunc eu vestibulum vehicula, odio nisl pellentesque quam, quis facilisis velit ipsum nec enim. Sed venenatis urna quis orci dictum fermentum. Curabitur eu hendrerit turpis, sit amet tristique massa. Integer et nulla a elit viverra dignissim nec non velit. Duis rhoncus quam id velit suscipit rutrum. Praesent scelerisque, lacus eget egestas mattis, mauris ante imperdiet libero, vitae egestas ligula tellus non orci. Etiam ultrices gravida enim ac fringilla."
        ),
        FAQItem(
            "Question 4",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse faucibus sapien ac euismod venenatis. Nullam at molestie leo. Fusce et sollicitudin nisl. Integer auctor augue non sem porta semper. Duis molestie feugiat augue, eget feugiat massa pellentesque at. Maecenas vestibulum mi quis bibendum finibus. Nunc viverra, nunc eu vestibulum vehicula, odio nisl pellentesque quam, quis facilisis velit ipsum nec enim. Sed venenatis urna quis orci dictum fermentum. Curabitur eu hendrerit turpis, sit amet tristique massa. Integer et nulla a elit viverra dignissim nec non velit. Duis rhoncus quam id velit suscipit rutrum. Praesent scelerisque, lacus eget egestas mattis, mauris ante imperdiet libero, vitae egestas ligula tellus non orci. Etiam ultrices gravida enim ac fringilla."
        ),
        FAQItem(
            "Question 5",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse faucibus sapien ac euismod venenatis. Nullam at molestie leo. Fusce et sollicitudin nisl. Integer auctor augue non sem porta semper. Duis molestie feugiat augue, eget feugiat massa pellentesque at. Maecenas vestibulum mi quis bibendum finibus. Nunc viverra, nunc eu vestibulum vehicula, odio nisl pellentesque quam, quis facilisis velit ipsum nec enim. Sed venenatis urna quis orci dictum fermentum. Curabitur eu hendrerit turpis, sit amet tristique massa. Integer et nulla a elit viverra dignissim nec non velit. Duis rhoncus quam id velit suscipit rutrum. Praesent scelerisque, lacus eget egestas mattis, mauris ante imperdiet libero, vitae egestas ligula tellus non orci. Etiam ultrices gravida enim ac fringilla."
        ),
        FAQItem(
            "Question 6",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse faucibus sapien ac euismod venenatis. Nullam at molestie leo. Fusce et sollicitudin nisl. Integer auctor augue non sem porta semper. Duis molestie feugiat augue, eget feugiat massa pellentesque at. Maecenas vestibulum mi quis bibendum finibus. Nunc viverra, nunc eu vestibulum vehicula, odio nisl pellentesque quam, quis facilisis velit ipsum nec enim. Sed venenatis urna quis orci dictum fermentum. Curabitur eu hendrerit turpis, sit amet tristique massa. Integer et nulla a elit viverra dignissim nec non velit. Duis rhoncus quam id velit suscipit rutrum. Praesent scelerisque, lacus eget egestas mattis, mauris ante imperdiet libero, vitae egestas ligula tellus non orci. Etiam ultrices gravida enim ac fringilla."
        ),

        )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp)) // Add space above the title

        Text(
            text = "Frequently Asked Questions",
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF7F462C),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(faqItems.size) { index ->
                FAQItemRow(faqItems[index])
            }
        }
    }
}


@Composable
fun FAQItemRow(faq: FAQItem) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(border = BorderStroke(2.dp, Color(0xFF7F462C)),
                shape = RoundedCornerShape(13.dp)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {


            Text(
                text = faq.question,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.weight(1f), color = Color(0xFF7F462C),
                fontSize = 20.sp
            )
            IconButton(
                onClick = { expanded = !expanded }
            ) {
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Expand/Collapse",
                    tint = Color(0xFF7F462C)
                )
            }
        }

        if (expanded) {
            Text(
                text = faq.answer,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(10.dp), color = Color(0xFF7F462C),
                fontSize = 15.sp,
                textAlign = TextAlign.Justify
            )
        }
    }
}

data class FAQItem(val question: String, val answer: String)

@Composable
fun FAQActivity(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFfff2e9)
    ) {
        FAQScreen()
    }
}
