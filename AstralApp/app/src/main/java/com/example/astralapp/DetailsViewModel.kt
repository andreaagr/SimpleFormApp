package com.example.astralapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class DetailsViewModel(private val resourceManager: ResourceManager) : ViewModel() {

    private val calendar = Calendar.getInstance()
    private val currentYear = calendar.get(Calendar.YEAR)
    private val mapOfZodiacDates = mapOf(
        resourceManager.ariesSign to Pair(Pair(21, 3), Pair(20, 4)),
        resourceManager.taurusSign to Pair(Pair(21, 4), Pair(20, 5)),
        resourceManager.geminiSign to Pair(Pair(21, 5), Pair(20, 6)),
        resourceManager.cancerSign to Pair(Pair(21, 6), Pair(20, 7)),
        resourceManager.leoSign to Pair(Pair(21, 7), Pair(21, 8)),
        resourceManager.virgoSign to Pair(Pair(22, 8), Pair(20, 9)),
        resourceManager.libraSign to Pair(Pair(21, 9), Pair(20, 10)),
        resourceManager.scorpioSign to Pair(Pair(21, 10), Pair(21, 11)),
        resourceManager.sagittariusSign to Pair(Pair(22, 11), Pair(21, 12)),
        resourceManager.capricornSign to Pair(Pair(22, 12), Pair(20, 1)),
        resourceManager.aquariusSign to Pair(Pair(21, 1), Pair(19, 2)),
        resourceManager.piscesSign to Pair(Pair(20, 2), Pair(20, 3))
    )
    private val mapOfChineseSigns = mapOf(
        resourceManager.oxSign to 1901,
        resourceManager.tigerSign to 1902,
        resourceManager.rabbitSign to 1903,
        resourceManager.dragonSign to 1904,
        resourceManager.snakeSign to 1905,
        resourceManager.horseSign to 1906,
        resourceManager.goatSign to 1907,
        resourceManager.monkeySign to 1908,
        resourceManager.roosterSign to 1909,
        resourceManager.dogSign to 1910,
        resourceManager.pigSign to 1911,
        resourceManager.ratSign to 1912
    )
    private var _userInformation = MutableLiveData<UserPresentation>()
    private var birthDateYear = 0
    private var birthDateMonth = 0
    private var birthDateDay = 0
    val userInformation: LiveData<UserPresentation> = _userInformation

    fun calculateUserDetails(user: User) {
        with(user) {
            extractDateElements(birthDate)
            val userPresentation = UserPresentation(
                fullName,
                getAge(birthDateYear),
                CardInfo(postalCode, R.drawable.direccion),
                getZodiacSign(
                    birthDateDay,
                    birthDateMonth
                ),
                getChineseSign(birthDateYear),
                getHobbyImage(hobby)
            )
            _userInformation.value = userPresentation
        }
    }

    private fun getAge(birthDateYear: Int): CardInfo {
        return CardInfo(
            (currentYear - birthDateYear).toString(),
            R.drawable.cake
        )
    }

    private fun getZodiacSign(day: Int, month: Int): CardInfo {
        var zodiacSign = ""
        mapOfZodiacDates.forEach { sign ->
            if (day >= sign.value.first.first &&
                month >= sign.value.first.second &&
                day <= sign.value.second.first &&
                month <= sign.value.second.second
            ) {
                zodiacSign = sign.key
            }
        }
        return  CardInfo(
            zodiacSign,
            getZodiacImage(zodiacSign)
        )
    }

    private fun getChineseSign(year: Int): CardInfo {
        var chineseSign = ""
        mapOfChineseSigns.forEach { sign ->
            if ((year - sign.value) % 12 == 0) {
                chineseSign = sign.key
            }
        }
        return CardInfo(
            chineseSign,
            getChineseImage(chineseSign)
        )
    }

    private fun getChineseImage(chineseSign: String): Int {
        return when(chineseSign) {
            resourceManager.oxSign -> R.drawable.ox
            resourceManager.tigerSign -> R.drawable.tiger
            resourceManager.rabbitSign -> R.drawable.rabbit
            resourceManager.dragonSign -> R.drawable.dragon
            resourceManager.snakeSign -> R.drawable.snake
            resourceManager.horseSign -> R.drawable.horse
            resourceManager.goatSign -> R.drawable.goat
            resourceManager.monkeySign -> R.drawable.monkey
            resourceManager.roosterSign -> R.drawable.rooster
            resourceManager.dogSign -> R.drawable.dog
            resourceManager.pigSign -> R.drawable.pig
            else -> R.drawable.rata
        }
    }

    private fun getZodiacImage(zodiacSign: String): Int {
        return when (zodiacSign) {
            resourceManager.ariesSign -> R.drawable.aries
            resourceManager.aquariusSign -> R.drawable.aquarius
            resourceManager.cancerSign -> R.drawable.cancer
            resourceManager.capricornSign -> R.drawable.capricorn
            resourceManager.geminiSign -> R.drawable.gemini
            resourceManager.leoSign -> R.drawable.leo
            resourceManager.virgoSign -> R.drawable.virgo
            resourceManager.libraSign -> R.drawable.libra
            resourceManager.scorpioSign -> R.drawable.scorpio
            resourceManager.sagittariusSign -> R.drawable.sagittarius
            resourceManager.piscesSign -> R.drawable.pisces
            else -> R.drawable.taurus
        }
    }

    private fun getHobbyImage(hobby: String): CardInfo {
        return CardInfo(
            hobby,
            when (hobby) {
                resourceManager.moviesHobby -> R.drawable.movie
                resourceManager.boardGamesHobby -> R.drawable.dominoes
                resourceManager.cookingHobby -> R.drawable.bake
                resourceManager.modelingHobby -> R.drawable.modelismo
                resourceManager.musicHobby -> R.drawable.guitarra
                resourceManager.photoHobby -> R.drawable.fotografo
                resourceManager.videogamesHobby -> R.drawable.gamepad
                resourceManager.theaterHobby -> R.drawable.teatro
                else -> R.drawable.yoga
            }
        )
    }

    private fun extractDateElements(birthDate: Date?) {
        if (birthDate != null) {
            calendar.time = birthDate
            birthDateYear = calendar.get(Calendar.YEAR)
            birthDateMonth = calendar.get(Calendar.MONTH)
            birthDateDay = calendar.get(Calendar.DAY_OF_MONTH)
        }
    }
}