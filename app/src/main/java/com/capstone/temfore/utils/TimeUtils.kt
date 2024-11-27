package com.capstone.temfore.utilsimport java.text.SimpleDateFormatimport java.util.Calendarimport java.util.Localeclass TimeUtils {    private val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())    private val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())    fun getCurrentTime(): String = timeFormat.format(Calendar.getInstance().time)    fun getCurrentDate(): String = dateFormat.format(Calendar.getInstance().time)    fun getTimeBasedMessage(): String {        return when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {            in 6..10 -> "Breakfast!🥪"            in 11..14 -> "Lunch!🧺"            in 18..20 -> "Dinner!🍴"            else -> "Snacking!☺️"        }    }    fun getHelloBasedMessage(): String {        return when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {            in 6..10 -> "Good Morning,"            in 11..14 -> "Good Afternoon,"            in 18..20 -> "Good Night,"            else -> "Welcome,"        }    }}