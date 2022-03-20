package com.example.astralapp

import android.content.Context
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