package com.example.apptema1

import android.content.Context
import android.content.SharedPreferences

class Preferences (context : Context) {
    companion object {
        const val PREFS_NAME = "myPhone"
        const val TASKS = "number"
    }

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

    //Guardar informacion
    fun saveNumber(number: String) {
        prefs.edit().putString(TASKS, number).apply()
    }
}