package com.example.stolpersteine

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.Voice
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.*

class TextToSpeechActivity(text: String): ViewModel() {

//    private val _state = mutableStateOf(MainScreenState())
//    val state: State<MainScreenState> = _state
    private  var  textToSpeech:TextToSpeech? = null


    fun textToSpeech(context: Context, text: MutableState<String>) {


        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech?.let { txtToSpeech ->
                    txtToSpeech.language = Locale.US
                    txtToSpeech.setSpeechRate(1.0f)

                    // Set the desired voice
                    val voice = Voice("en-us-x-sfg#male_2-local", Locale.US, Voice.QUALITY_VERY_HIGH, 1, false, null)

                    txtToSpeech.voice = voice


                    txtToSpeech.speak(
                        text.value,
                        TextToSpeech.QUEUE_ADD,
                        null,
                        null
                    )
                }
            }

        }
    }

}