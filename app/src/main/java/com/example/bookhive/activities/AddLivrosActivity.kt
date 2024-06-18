package com.example.bookhive.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookhive.R
import com.example.bookhive.objects.Livro
import org.json.JSONArray

class AddLivrosActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livros)

        sharedPreferences = getSharedPreferences("livros_prefs", Context.MODE_PRIVATE)

        val editTitleBook: EditText = findViewById(R.id.edit_book_title)
        val editAuthorBook: EditText = findViewById(R.id.edit_book_author)
        val editAnoPubli: EditText = findViewById(R.id.edit_ano_publi)
        val editGenero: EditText = findViewById(R.id.edit_book_genero)
        val editBookSinopse: EditText = findViewById(R.id.edit_book_sinopse)

        val btnAddBook: Button = findViewById(R.id.btnAddBook)

        val navigateHomeAddBook: Button = findViewById(R.id.navigateHomeAddBook)

        btnAddBook.setOnClickListener {
            val title = editTitleBook.text.toString().trim()
            val author = editAuthorBook.text.toString().trim()
            val anoPubli = editAnoPubli.text.toString().trim()
            val genero = editGenero.text.toString().trim()
            val sinopse = editBookSinopse.text.toString().trim()

            val validationMessage = validateFields(title, author, anoPubli, genero, sinopse)

            if (validationMessage.isEmpty()) {
                val livro = Livro(title, author, anoPubli, genero, sinopse)
                salvarLivro(livro)
                exibirToast("Livro adicionado com sucesso!")
                recuperarLivros()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                exibirToast(validationMessage)
            }
        }

        navigateHomeAddBook.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun validateFields(title: String, author: String, anoPubli: String, genero: String, sinopse: String): String {
        val errorMessages = mutableListOf<String>()

        if (title.isEmpty()) {
            errorMessages.add("Título do livro é obrigatório.")
        }

        if (author.isEmpty()) {
            errorMessages.add("Autor do livro é obrigatório.")
        }

        if (anoPubli.isEmpty()) {
            errorMessages.add("Ano de publicação é obrigatório.")
        } else if (!anoPubli.matches(Regex("\\d{4}"))) {
            errorMessages.add("Ano de publicação deve ser um ano válido (ex: 2022).")
        }

        if (genero.isEmpty()) {
            errorMessages.add("Gênero do livro é obrigatório.")
        }

        if (sinopse.isEmpty()) {
            errorMessages.add("Sinopse do livro é obrigatória.")
        }

        return errorMessages.joinToString("\n")
    }

    private fun salvarLivro(livro: Livro) {
        val livros = recuperarListaLivros().toMutableList()
        livros.add(livro)

        val editor = sharedPreferences.edit()
        val jsonArray = JSONArray()
        for (livro in livros) {
            jsonArray.put(livro.toJsonString())
        }
        editor.putString("livros", jsonArray.toString())
        editor.apply()
    }

    private fun recuperarLivros() {
        val livros = recuperarListaLivros()
        for (livro in livros) {
            Log.d("LivrosActivity", "Livro: $livro")
        }
    }

    private fun recuperarListaLivros(): List<Livro> {
        val livros = mutableListOf<Livro>()
        val jsonArrayString = sharedPreferences.getString("livros", "[]")
        val jsonArray = JSONArray(jsonArrayString)
        for (i in 0 until jsonArray.length()) {
            val jsonString = jsonArray.getString(i)
            val livro = Livro.fromJsonString(jsonString)
            livros.add(livro)
        }
        return livros
    }

    private fun exibirToast(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }
}
