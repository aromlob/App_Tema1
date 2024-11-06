package com.example.apptema1

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var ibtnPhone : ImageButton
    lateinit var ibtnSearch : ImageButton
    lateinit var ibtnMap : ImageButton
    lateinit var ibtnAlarm : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initEvent()
    }
    private fun initEvent(){
        ibtnPhone = findViewById(R.id.ibtn_phone)
        ibtnPhone.setOnClickListener {
            val intent = Intent(this, CallPhone::class.java)
            startActivity(intent)
        }

        ibtnSearch = findViewById(R.id.ibtn_search)
        ibtnSearch.setOnClickListener {
            val intent = Intent(this, Search::class.java)
            startActivity(intent)
        }

        ibtnMap = findViewById(R.id.ibtn_map)
        ibtnMap.setOnClickListener {
            val intent = Intent(this, Map::class.java)
            startActivity(intent)
        }

        ibtnAlarm = findViewById(R.id.ibtn_alarm)
        ibtnAlarm.setOnClickListener {
            val intent = Intent(this, NewAlarm::class.java)
            startActivity(intent)
        }
    }
}