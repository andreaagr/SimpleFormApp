package com.example.astralapp.model.presentation

import java.util.*

data class UserPresentation (
    val fullName: String,
    val age: CardInfo,
    val postalCode: CardInfo,
    val zodiacSign: CardInfo,
    val chineseSign: CardInfo,
    val hobby: CardInfo,
    val birthDate: Date?
)