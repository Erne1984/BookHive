package com.example.bookhive.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

            val validationMessage = validateFields(nome, nacionalidade, dataNascimento)

            if (validationMessage.isEmpty()) {
                val autor = Autor(nome, nacionalidade, dataNascimento, biografia)
                exibirToast("Autor adicionado com sucesso!")
                salvarAutor(autor)
                finish()
            } else {
                exibirToast(validationMessage)
            }
        }
    }

    private fun validateFields(nome: String, nacionalidade: String, dataNascimento: String): String {
        val errorMessages = mutableListOf<String>()

        if (nome.isEmpty()) {
            errorMessages.add("Nome do autor é obrigatório.")
        }

        if (nacionalidade.isEmpty()) {
            errorMessages.add("Nacionalidade do autor é obrigatória.")
        }

        if (dataNascimento.isEmpty()) {
            errorMessages.add("Data de nascimento do autor é obrigatória.")
        }

        return errorMessages.joinToString("\n")
    }

    private fun salvarAutor(autor: Autor) {
        val jsonArrayString = sharedPreferences.getString("autores", "[]")
        val jsonArray = JSONArray(jsonArrayString)
        val jsonObject = JSONObject(autor.toJsonString())
        jsonArray.put(jsonObject)

        val editor = sharedPreferences.edit()
        editor.putString("autores", jsonArray.toString())
        editor.apply()
    }

    private fun exibirToast(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }
}
