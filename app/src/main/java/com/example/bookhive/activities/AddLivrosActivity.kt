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

        btnAddBook.setOnClickListener {
            val title = editTitleBook.text.toString()
            val author = editAuthorBook.text.toString()
            val anoPubli = editAnoPubli.text.toString()
            val genero = editGenero.text.toString()
            val sinopse = editBookSinopse.text.toString()

            if (title.isEmpty() || author.isEmpty() || anoPubli.isEmpty() || genero.isEmpty() || sinopse.isEmpty()) {
                exibirToast("Por favor, preencha todos os campos!")
                return@setOnClickListener
            }

            val livro = Livro(title, author, anoPubli, genero, sinopse)

            salvarLivro(livro)
            exibirToast("Livro adicionado com sucesso!")
            recuperarLivros()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
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
