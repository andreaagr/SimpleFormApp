package com.example.astralapp.utils

import android.content.Context
import com.example.astralapp.R

class ResourceManager(private val context: Context) {
    val moviesHobby get() = context.getString(R.string.movies_hobby)
    val cookingHobby get() = context.getString(R.string.cooking_hobby)
    val photoHobby get() = context.getString(R.string.photo_hobby)
    val boardGamesHobby get() = context.getString(R.string.board_games_hobby)
    val modelingHobby get() = context.getString(R.string.modeling_hobby)
    val musicHobby get() = context.getString(R.string.music_hobby)
    val theaterHobby get() = context.getString(R.string.theater_hobby)
    val videogamesHobby get() = context.getString(R.string.videogames_hobby)
    val ariesSign get() = context.getString(R.string.zodiac_sign_aries)
    val capricornSign get() = context.getString(R.string.zodiac_sign_capricorn)
    val geminiSign get() = context.getString(R.string.zodiac_sign_gemini)
    val virgoSign get() = context.getString(R.string.zodiac_sign_virgo)
    val leoSign get() = context.getString(R.string.zodiac_sign_leo)
    val aquariusSign get() = context.getString(R.string.zodiac_sign_aquarius)
    val cancerSign get() = context.getString(R.string.zodiac_sign_cancer)
    val libraSign get() = context.getString(R.string.zodiac_sign_libra)
    val scorpioSign get() = context.getString(R.string.zodiac_sign_scorpio)
    val taurusSign get() = context.getString(R.string.zodiac_sign_taurus)
    val piscesSign get() = context.getString(R.string.zodiac_sign_pisces)
    val sagittariusSign get() = context.getString(R.string.zodiac_sign_sagittarius)
    val oxSign get() = context.getString(R.string.chinese_sign_ox)
    val tigerSign get() = context.getString(R.string.chinese_sign_tiger)
    val rabbitSign get() = context.getString(R.string.chinese_sign_rabbit)
    val dragonSign get() = context.getString(R.string.chinese_sign_dragon)
    val snakeSign get() = context.getString(R.string.chinese_sign_snake)
    val horseSign get() = context.getString(R.string.chinese_sign_horse)
    val goatSign get() = context.getString(R.string.chinese_sign_goat)
    val monkeySign get() = context.getString(R.string.chinese_sign_monkey)
    val roosterSign get() = context.getString(R.string.chinese_sign_rooster)
    val dogSign get() = context.getString(R.string.chinese_sign_dog)
    val pigSign get() = context.getString(R.string.chinese_sign_pig)
    val ratSign get() = context.getString(R.string.chinese_sign_rat)
}