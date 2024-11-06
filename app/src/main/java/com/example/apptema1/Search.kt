package com.example.apptema1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
/**
 * Clase Search -> Permite abrir enlaces web en un navegador
 * Hereda de la clase AppCompatActivity
 *
 * @author Ángela Romero Lobo
 * @version 1.0.0
 */
class Search : AppCompatActivity() {
    //Se define los diferentes botones para abrir enlaces web y volver al inicio
    lateinit var btnzara : ImageButton
    lateinit var btnmercadona : ImageButton
    lateinit var btndiarioJaen : ImageButton
    lateinit var ibtn_Back : ImageButton

    //Constantes de la clase Search
    companion object{
        const val NOT_FOUND =" No se puede abrir porque no hay un navegador disponible"
        const val URL_ZARA = "https://www.zara.com"
        const val URL_MERCADONA = "https://www.mercadona.es"
        const val URL_PERIODICO ="https://www.diariojaen.es"
    }

    //Metodo que se ejecuta al iniciar la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Establece el diseño de la clase Search
        setContentView(R.layout.activity_search)

        //Se asigna a la variable el boton correspondiente  desde el layout
        btnzara = findViewById(R.id.btn_zara)
        btnmercadona = findViewById(R.id.btn_mercadona)
        btndiarioJaen = findViewById(R.id.btn_diariojaen)
        ibtn_Back = findViewById(R.id.iv_back)

        // Llama al metodo para inicializar eventos de los botones
        initEvent()
    }

    //Metodo que  inicializa los eventos de los diferentes botones de la interfaz
    private fun initEvent() {

        // Botones que al pulsar en ellos, abren las URL correspondientes en el navegador
        btnzara.setOnClickListener{
            openUrl(URL_ZARA)
        }
        btnmercadona.setOnClickListener {
            openUrl(URL_MERCADONA)
        }
        btndiarioJaen.setOnClickListener {
            openUrl(URL_PERIODICO)
        }

        //Se establece el funcionamiento del boton cuando pulsemos sobre el, volviendo al inicio
        ibtn_Back.setOnClickListener {
            val intentReturn = Intent(this, MainActivity::class.java)
            startActivity(intentReturn)
            finish()
        }
    }

    /**
     * Metodo que intenta abrir una URL en el navegador
     * Muestra un mensaje si no hay navegador disponible
     */
    private fun openUrl(url: String){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        if (intent != null) {
            startActivity(intent)
        }else {
            Toast.makeText(this, NOT_FOUND, Toast.LENGTH_LONG).show()
        }
    }
}