package com.example.apptema1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Alarma creada", Toast.LENGTH_SHORT).show()

    }
}