package com.example.apptema1

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.util.Calendar
import android.os.Bundle
import android.os.SystemClock
import android.provider.AlarmClock
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * Clase NewAlarm -> Permite crear una alarma para dentro de 2 minutos
 * Hereda de la clase AppCompatActivity
 *
 * @author Ángela Romero Lobo
 * @version 1.0.0
 */
class NewAlarm : AppCompatActivity() {
    //Se define los diferentes botones para abrir enlaces web y volver al inicio
    lateinit var ibtn_Back : ImageButton
    lateinit var btn_alarma : Button
    //Metodo que se ejecuta al iniciar la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Establece el diseño de la clase NewAlarm
        setContentView(R.layout.activity_new_alarm)

        //Se asigna a la variable el boton correspondiente  desde el layout
        btn_alarma = findViewById(R.id.btn_alarma)
        ibtn_Back = findViewById(R.id.iv_backhome)

        // Llama al metodo para inicializar eventos de los botones
        initEvent()
    }

    //Metodo que  inicializa los eventos de los diferentes botones de la interfaz
    private fun initEvent() {
        //Evento para el btn_alarma que crea una alarma
        btn_alarma.setOnClickListener { createAlarm()}
        //Evento para el ibtn_back para volver al inicio de la aplicacion
        ibtn_Back.setOnClickListener {
            val intentReturn = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intentReturn)
            finish()
        }
    }

    //Metodo que crea una alarma para dentro de 2 minutos
    private fun createAlarm() {
        //Se obtiene la hora actual del dispositivo
        val calendar = Calendar.getInstance()
        val alarmHour = calendar.get(Calendar.HOUR_OF_DAY)
        //Minutos del sistema + 2 minutos
        val alarmMin = calendar.get(Calendar.MINUTE) + 2

        //Sr configura la alarma con la hora y los minutos del sistema
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_HOUR, alarmHour)
            putExtra(AlarmClock.EXTRA_MINUTES, alarmMin)
            putExtra(AlarmClock.EXTRA_MESSAGE, "La alarma saltará a las $alarmHour:$alarmMin")
        }

        //Verifica si el permiso de alarma esta aceptado o no
        if (ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.SET_ALARM
            ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SET_ALARM), 1)
        } else {
            startActivity(intent)
        }
    }
}