package com.example.bookhive

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId) {
            R.id.Search -> {
                Toast.makeText(this, "you clicked Search!", Toast.LENGTH_LONG).show()
                return true
            } R.id.Favorite -> {
                Toast.makeText(this, "you clicked Favorite!", Toast.LENGTH_LONG).show()
                return true
            } R.id.Share -> {
                Toast.makeText(this, "you clicked Share!", Toast.LENGTH_LONG).show()
                return true
            } R.id.Whatsapp -> {
                Toast.makeText(this, "you clicked Whatsapp!", Toast.LENGTH_LONG).show()
                return true
            } R.id.Instagram -> {
                Toast.makeText(this, "you clicked Instagram!", Toast.LENGTH_LONG).show()
                return true
            } else -> super.onOptionsItemSelected(item)
        }
    }
}