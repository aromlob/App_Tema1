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
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class CallPhone : AppCompatActivity() {
    lateinit var etNumberPhone: EditText
    lateinit var btnPhone : Button
    lateinit var ibtn_Back : ImageButton
    lateinit var prefs : Preferences

    lateinit var btn_ChangePhone : Button
    companion object{
        const val PERMISION = "Se requiere habilitar los permisos"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_phone)
        etNumberPhone = findViewById(R.id.et_telefono)
        btnPhone = findViewById(R.id.btn_telef)
        ibtn_Back = findViewById(R.id.iv_back)
       // btn_ChangePhone = findViewById(R.id.btn_Change)
        initEvent()
    }
    private fun initEvent(){
        btnPhone.setOnClickListener {requestPermissions()}
        ibtn_Back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }
        //btn_ChangePhone.setOnClickListener{
            //addNUmber()
            //startActivity(intent)
        //}
    }

    private fun requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                call()
            }else {
                requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
                Toast.makeText(this, "Permiso Denegado", Toast.LENGTH_LONG).show()
            }
            Toast.makeText(this, "Permisos aceptados", Toast.LENGTH_LONG).show()
        }else{
            call()
        }
    }

    private fun call() {
        startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:${etNumberPhone.text}")))
    }

    private val requestPermissionLauncher = registerForActivityResult( ActivityResultContracts.RequestPermission() ){ isGranted ->
        if (isGranted) {
            call()
        }else{
            Toast.makeText(this, PERMISION, Toast.LENGTH_LONG).show()
        }
    }

    private fun addNumber() {
        val number : String = etNumberPhone.text.toString()
        prefs.saveNumber(number)
    }
}