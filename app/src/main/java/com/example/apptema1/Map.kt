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

class Map : AppCompatActivity() {
    lateinit var btnmapa : Button
    lateinit var ibtn_Back : ImageButton
    companion object{
        const val LOCATION = "geo:0,0?q=my+street+address"
        const val PERMISION = "Se requiere habilitar los permisos"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_map)
        btnmapa = findViewById(R.id.btn_mapa)
        ibtn_Back = findViewById(R.id.iv_backhome)
        initEvent()
    }

    private fun initEvent() {
        btnmapa.setOnClickListener{requestPermissions()}
        ibtn_Back.setOnClickListener {
            val intentReturn = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intentReturn)
            finish()
        }
    }
    private fun requestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            openMap(LOCATION)
        }else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }
    private fun openMap(locationMap : String){
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(locationMap)
            setPackage("com.google.android.apps.maps")
        }
        startActivity(intent)
    }
    private val requestPermissionLauncher = registerForActivityResult( ActivityResultContracts.RequestPermission() ){ isGranted ->
        if (isGranted) {
            openMap(LOCATION)
        }else{
            Toast.makeText(this, PERMISION, Toast.LENGTH_LONG).show()
        }
    }

}