package com.example.bookhive

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input: EditText = findViewById(R.id.editTxt)
        val btn: Button = findViewById(R.id.btn1)
        val txt: TextView = findViewById(R.id.txtView)

        val btnNavigate: Button = findViewById(R.id.btnNavigate)

        btn.setOnClickListener{

            val inputText = input.text.toString()

            txt.text = inputText
        }

        btnNavigate.setOnClickListener {
            val intent = Intent(this, activity_livros::class.java)
            startActivity(intent)
        }

    }
}