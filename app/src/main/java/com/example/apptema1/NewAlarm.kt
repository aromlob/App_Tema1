package com.example.apptema1

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.os.SystemClock
import android.provider.AlarmClock
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NewAlarm : AppCompatActivity() {
    lateinit var ibtn_Back : ImageButton
    lateinit var btn_alarma : Button
    private var alarmManager : AlarmManager ?= null
    private lateinit var alarmIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_alarm)
        btn_alarma = findViewById(R.id.btn_alarma)
        ibtn_Back = findViewById(R.id.iv_backhome)
        initEvent()
    }

    private fun initEvent() {
        btn_alarma.setOnClickListener { createAlarm()}
        ibtn_Back.setOnClickListener {
            val intentReturn = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intentReturn)
            finish()
        }
    }

    private fun createAlarm() {
           alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
           alarmManager?.setInexactRepeating(
               AlarmManager.ELAPSED_REALTIME_WAKEUP,
               SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_HALF_HOUR,
               AlarmManager.INTERVAL_HALF_HOUR,
               alarmIntent
           )

           alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
           alarmIntent = Intent(this, AlarmReceiver::class.java).let {
               PendingIntent.getBroadcast(this, 0, it, PendingIntent.FLAG_UPDATE_CURRENT )
           }

           alarmManager?.set(
               AlarmManager.ELAPSED_REALTIME_WAKEUP,
               SystemClock.elapsedRealtime() + 2 * 60 * 1000,
               alarmIntent
           )
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
            Toast.makeText(this, "Alarma creada para que salte en 2 minutos", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "No se pudo abrir la app de alarmas", Toast.LENGTH_LONG).show()
        }
    }
}