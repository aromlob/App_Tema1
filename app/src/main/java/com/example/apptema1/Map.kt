package com.example.apptema1

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
/**
 * Clase Map -> Permite abrir el Google maps con una dirección específica
 * Hereda de la clase AppCompatActivity
 *
 * @author Ángela Romero Lobo
 * @version 1.0.0
 */
class Map : AppCompatActivity() {

    //Se define los diferentes botones para abrir el mapa y volver al inicio
    lateinit var btnmapa : Button
    lateinit var ibtn_Back : ImageButton

    //Constantes de la clase Map
    companion object{
        const val LOCATION = "geo:0,0?q=my+street+address"
        const val PERMISION = "Se requiere habilitar los permisos"

    }

    //Se ejecuta cuando se incia la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Establece el diseño de la clase Map
        setContentView(R.layout.activity_open_map)

        //Se asigna a la variable el boton correspondiente  desde el layout
        btnmapa = findViewById(R.id.btn_mapa)
        ibtn_Back = findViewById(R.id.iv_backhome)

        // Llama al metodo para inicializar eventos de los botones
        initEvent()
    }

    //Metodo que  inicializa los eventos de los diferentes botones de la interfaz
    private fun initEvent() {
        //Se establece el funcionamiento del boton cuando pulsemos sobre el
        btnmapa.setOnClickListener{requestPermissions()}

        //Se establece el funcionamiento del boton cuando pulsemos sobre el
        ibtn_Back.setOnClickListener {
            val intentReturn = Intent(this, MainActivity::class.java)
            // Finaliza la actividad actual
            finish()
            // Inicia la actividad principal
            startActivity(intentReturn)
            finish()
        }
    }

    // Metodo para solicitar permisos de ubicación del usuario
    private fun requestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            openMap(LOCATION)
        }else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    //Metodo que se encarga de abrir el mapa
    private fun openMap(locationMap : String){
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(locationMap)
            setPackage("com.google.android.apps.maps")
        }
        startActivity(intent)
    }

    //Metodo que comprueba que los permisos estan activados
    private val requestPermissionLauncher = registerForActivityResult( ActivityResultContracts.RequestPermission() ){ isGranted ->

        //Abrira el mapa si los permisos estan activos, si no mostrara un mensaje de que se necesitan habilitar los permisos
        if (isGranted) {
            openMap(LOCATION)
        }else{
            Toast.makeText(this, PERMISION, Toast.LENGTH_LONG).show()
        }
    }

}