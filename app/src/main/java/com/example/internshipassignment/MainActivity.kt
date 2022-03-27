package com.example.internshipassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var siteurl_ev: EditText
    private lateinit var username_ev: EditText
    private lateinit var password_ev: EditText
    private lateinit var get_btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        siteurl_ev = findViewById(R.id.ev_siteurl)
        username_ev = findViewById(R.id.ev_username)
        password_ev = findViewById(R.id.ev_password)
        get_btn = findViewById(R.id.btn_get)

        get_btn.setOnClickListener()
        {
            val intent = Intent(this, LoginActivity::class.java)
            val siteurl = siteurl_ev.text.toString()
            val username = username_ev.text.toString()
            val password = password_ev.text.toString()
            intent.putExtra("siteurl", siteurl)
            intent.putExtra("username", username)
            intent.putExtra("password", password)
            startActivity(intent)
        }
    }
}