package com.example.astralapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class User (
    val fullName: String,
    val birthDate: Date?,
    val postalCode: String,
    val hobby: String
) : Parcelable