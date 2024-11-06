package com.example.apptema1

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
/**
 * Clase MainActivity define las diferentes partes de funcionamiento de la app
 * Hereda de la clase AppCompatActivity
 *
 * @author Ángela Romero Lobo
 * @version 1.0.0
 */
class MainActivity : AppCompatActivity() {

    //Se define los diferentes botones establecidos en el activity_main
    lateinit var ibtnPhone : ImageButton
    lateinit var ibtnSearch : ImageButton
    lateinit var ibtnMap : ImageButton
    lateinit var ibtnAlarm : ImageButton

    //Se ejecuta cuando se incia la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Se establece el diseño de la clase MainActivity
        setContentView(R.layout.activity_main)

        // Llama al metodo para inicializar eventos de los botones
        initEvent()
    }

    //Metodo que  inicializa los eventos de los diferentes botones de la interfaz
    private fun initEvent(){

        //Se asigna a esta variable el boton del telofono desde el layout
        ibtnPhone = findViewById(R.id.ibtn_phone)

        //Se define el funcionamiento del boton cuando pulsemos sobre el
        ibtnPhone.setOnClickListener {
            val intent = Intent(this, CallPhone::class.java)
            startActivity(intent)
        }

        //Se asigna a esta variable el boton del buscar desde el layout
        ibtnSearch = findViewById(R.id.ibtn_search)

        //Se define el funcionamiento del boton cuando pulsemos sobre el
        ibtnSearch.setOnClickListener {
            val intent = Intent(this, Search::class.java)
            startActivity(intent)
        }

        //Se asigna a esta variable el boton del mapa desde el layout
        ibtnMap = findViewById(R.id.ibtn_map)

        //Se define el funcionamiento del boton cuando pulsemos sobre el
        ibtnMap.setOnClickListener {
            val intent = Intent(this, Map::class.java)
            startActivity(intent)
        }

        //Se asigna a esta variable el boton del alarma desde el layout
        ibtnAlarm = findViewById(R.id.ibtn_alarm)

        //Se define el funcionamiento del boton cuando pulsemos sobre el
        ibtnAlarm.setOnClickListener {
            val intent = Intent(this, NewAlarm::class.java)
            startActivity(intent)
        }
    }
}