package com.example.apptema1

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

/**
 * Clase CallPhone -> Permite realizar una llamada a cualquier numero de telefono
 * Hereda de la clase AppCompatActivity
 *
 * @author Ángela Romero Lobo
 * @version 1.0.0
 */
class CallPhone : AppCompatActivity() {
    // Campo de entrada para el número de teléfono
    private lateinit var etNumberPhone: EditText

    // Botón para iniciar la llamada
    private lateinit var btnPhone: Button

    // Botón de imagen para regresar a la pantalla principal
    private lateinit var ibtnBack: ImageButton

    // TextView para mostrar el último número guardado
    private lateinit var tvSavedNumber: TextView

    // Helper de preferencias compartidas para almacenar y recuperar datos
    private lateinit var preferences: SharedPreferences

    //Constantes de la clase CallPhone
    companion object {
        const val PERMISSION_MSG = "Se requiere habilitar los permisos"
        const val ERROR_NUMBER = "Número de teléfono vacío"
    }

    //Metodo que inicia la aplicación
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Establece el diseño de la clase Call Phone
        setContentView(R.layout.activity_call_phone)

        //Se asigna a la variable el elemento correspondiente  desde el layout
        etNumberPhone = findViewById(R.id.et_telefono)
        btnPhone = findViewById(R.id.btn_telef)
        ibtnBack = findViewById(R.id.iv_back)
        tvSavedNumber = findViewById(R.id.tv_number)

        //Incializa la clase SharedPreferences
        preferences = SharedPreferences(this)

        // Carga el número almacenado en el tvSavedNumber al iniciar la actividad
        tvSavedNumber.text = "Número guardado: ${preferences.getNumber()}"
        etNumberPhone.setText(preferences.getNumber())

        // Llama al metodo para inicializar eventos de los botones
        initEvent()
    }

    //Metodo que  inicializa los eventos de los diferentes elementos de la interfaz
    private fun initEvent() {
        //Evento de llamada
        btnPhone.setOnClickListener { requestPermissions() }

        //Evento de volver al incio
        ibtnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    /**
     * Metodo que solicita permisos para realizar la llamada
     * Verifica que tipo de version de sdk es
     * Y comprueba si los permisos son aceptados o no
     */
    private fun requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                call()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
            }
        } else {
            call()
        }
    }

    /**
     * Metodo que realiza la llamada
     * Verifica si se a introducido un numero o no
     */
    private fun call() {
        val phoneNumber = etNumberPhone.text.toString()
        if (phoneNumber.isNotEmpty()) {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber"))
            startActivity(intent)

            // Almacena el número introducido
            preferences.saveNumber(phoneNumber)
            // Actualiza el textview con el numero introducido
            tvSavedNumber.text = "Número guardado: $phoneNumber"
        } else {
            Toast.makeText(this, ERROR_NUMBER, Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Metodo que comprueba que los permisos han sido aceptados o no
     */
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            call()
        } else {
            Toast.makeText(this, PERMISSION_MSG, Toast.LENGTH_LONG).show()
        }
    }
}
