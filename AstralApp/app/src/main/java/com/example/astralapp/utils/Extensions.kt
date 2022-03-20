package com.example.astralapp.utils

import android.content.Context
import com.example.astralapp.R
import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(context: Context): Date? =
    SimpleDateFormat(
        context.getString(R.string.calendar_date_format),
        Locale.getDefault()
    ).parse(this)

fun Long.toStringDate(context: Context): String =
    SimpleDateFormat(
        context.getString(R.string.calendar_date_format),
        Locale.getDefault()
    ).format(this)

fun Date.toStringDate(context: Context): String =
    SimpleDateFormat(
        context.getString(R.string.calendar_date_format),
        Locale.getDefault()
    ).format(this)