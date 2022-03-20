package com.example.astralapp.model.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class User (
    val fullName: String,
    val birthDate: Date?,
    val postalCode: String,
    val hobby: String
) : Parcelable