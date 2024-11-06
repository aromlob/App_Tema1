package com.example.apptema1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Search : AppCompatActivity() {
    lateinit var btnzara : ImageButton
    lateinit var btnmercadona : ImageButton
    lateinit var btndiarioJaen : ImageButton
    lateinit var ibtn_Back : ImageButton

    companion object{
        const val NOT_FOUND =" No se puede abrir porque no hay un navegador disponible"
        const val URL_ZARA = "https://www.zara.com"
        const val URL_MERCADONA = "https://www.mercadona.es"
        const val URL_PERIODICO ="https://www.diariojaen.es"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        btnzara = findViewById(R.id.btn_zara)
        btnmercadona = findViewById(R.id.btn_mercadona)
        btndiarioJaen = findViewById(R.id.btn_diariojaen)
        ibtn_Back = findViewById(R.id.iv_back)
        initEvent()
    }

    private fun initEvent() {
        btnzara.setOnClickListener{
            openUrl(URL_ZARA)
        }
        btnmercadona.setOnClickListener {
            openUrl(URL_MERCADONA)
        }
        btndiarioJaen.setOnClickListener {
            openUrl(URL_PERIODICO)
        }
        ibtn_Back.setOnClickListener {
            val intentReturn = Intent(this, MainActivity::class.java)
            startActivity(intentReturn)
            finish()
        }
    }

    private fun openUrl(url: String){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        if (intent != null) {
            startActivity(intent)
        }else {
            Toast.makeText(this, NOT_FOUND, Toast.LENGTH_LONG).show()
        }
    }
}