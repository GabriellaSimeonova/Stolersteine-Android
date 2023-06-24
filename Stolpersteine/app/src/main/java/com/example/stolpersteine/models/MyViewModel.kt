package com.example.stolpersteine.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    val sharedObject = mutableStateOf<Stone?>(null)
}
