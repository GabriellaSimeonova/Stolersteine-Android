package com.example.stolpersteine.models

data class Stone(val id: Int,
                 val city: String,
                 val officialStone: Boolean,
                 val address: String,
                 val name: String,
                 val dateOfBirth: String,
                 val dateOfPassing: String,
                 val placeOfPassing: String,
                 val reasonOfPassing: String,
                 val gender: String,
                 val photo: String,
                 val url: String?,
                 val niodUrl: String?,
                 val mapUrl: String,
                 val location: Location)

data class Location(
    val lat: Double,
    val long: Double
)

