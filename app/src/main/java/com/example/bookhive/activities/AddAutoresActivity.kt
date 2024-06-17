package com.example.bookhive.activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.bookhive.R
import com.example.bookhive.objects.Autor
import org.json.JSONArray
import org.json.JSONObject

class AddAutoresActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_autores)

        sharedPreferences = getSharedPreferences("autores_prefs", Context.MODE_PRIVATE)

        val editAuthorName = findViewById<EditText>(R.id.edit_author_name)
        val editNationalityAuthor = findViewById<EditText>(R.id.edit_nationality_author)
        val editBirthdayAuthor = findViewById<EditText>(R.id.edit_birthday_author)
        val editBiographyAuthor = findViewById<EditText>(R.id.edit_biography_author)
        val btnAddAuthor = findViewById<Button>(R.id.btnAddAuthor)

        btnAddAuthor.setOnClickListener {
            val nome = editAuthorName.text.toString().trim()
            val nacionalidade = editNationalityAuthor.text.toString().trim()
            val dataNascimento = editBirthdayAuthor.text.toString().trim()
            val biografia = editBiographyAuthor.text.toString().trim()

            if (nome.isNotEmpty() && nacionalidade.isNotEmpty() && dataNascimento.isNotEmpty()) {
                val autor = Autor(nome, nacionalidade, dataNascimento, biografia)
                exibirToast("Livro adicionado com sucesso!")
                salvarAutor(autor)
                finish()
            } else {
                exibirToast("Por favor, preencha todos os campos!")
            }
        }
    }

    private fun salvarAutor(autor: Autor) {
        val jsonArrayString = sharedPreferences.getString("autores", "[]")
        val jsonArray = JSONArray(jsonArrayString)
        val jsonObject = JSONObject(autor.toJsonString())
        jsonArray.put(jsonObject)

        val editor = sharedPreferences.edit()
        editor.putString("autores", jsonArray.toString())
        editor.apply()

        Log.d("AddAutoresActivity", "Autor adicionado: $autor")
    }

    private fun exibirToast(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }
}
