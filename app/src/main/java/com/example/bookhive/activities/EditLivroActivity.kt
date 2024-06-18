package com.example.bookhive.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookhive.R

class EditLivroActivity : AppCompatActivity() {

    private lateinit var tituloEditText: EditText
    private lateinit var autorEditText: EditText
    private lateinit var anoPubliEditText: EditText
    private lateinit var generoEditText: EditText
    private lateinit var sinopseEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_livro)

        tituloEditText = findViewById(R.id.editTextTitulo)
        autorEditText = findViewById(R.id.editTextAutor)
        anoPubliEditText = findViewById(R.id.editTextAnoPubli)
        generoEditText = findViewById(R.id.editTextGenero)
        sinopseEditText = findViewById(R.id.editTextSinopse)
        saveButton = findViewById(R.id.saveButton)

        val livroId = intent.getIntExtra("livro_id", -1)
        val livroTitulo = intent.getStringExtra("livro_titulo")
        val livroAutor = intent.getStringExtra("livro_autor")
        val livroAnoPubli = intent.getStringExtra("livro_anoPubli")
        val livroGenero = intent.getStringExtra("livro_genero")
        val livroSinopse = intent.getStringExtra("livro_sinopse")

        tituloEditText.setText(livroTitulo)
        autorEditText.setText(livroAutor)
        anoPubliEditText.setText(livroAnoPubli)
        generoEditText.setText(livroGenero)
        sinopseEditText.setText(livroSinopse)

        val navigateList: Button = findViewById(R.id.navigateListBook)

        saveButton.setOnClickListener {
            val titulo = tituloEditText.text.toString().trim()
            val autor = autorEditText.text.toString().trim()
            val anoPubli = anoPubliEditText.text.toString().trim()
            val genero = generoEditText.text.toString().trim()
            val sinopse = sinopseEditText.text.toString().trim()

            if (validateInputs(titulo, autor, anoPubli, genero, sinopse)) {
                val resultIntent = Intent()
                resultIntent.putExtra("livro_id", livroId)
                resultIntent.putExtra("livro_titulo", titulo)
                resultIntent.putExtra("livro_autor", autor)
                resultIntent.putExtra("livro_anoPubli", anoPubli)
                resultIntent.putExtra("livro_genero", genero)
                resultIntent.putExtra("livro_sinopse", sinopse)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()

                Toast.makeText(this, "Livro editado com sucesso!", Toast.LENGTH_SHORT).show()
            }
        }

        navigateList.setOnClickListener{
            val intent = Intent(this, ListLivros::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun validateInputs(titulo: String, autor: String, anoPubli: String, genero: String, sinopse: String): Boolean {
        var isValid = true

        if (titulo.isEmpty()) {
            tituloEditText.error = "Título é obrigatório"
            isValid = false
        }

        if (autor.isEmpty()) {
            autorEditText.error = "Autor é obrigatório"
            isValid = false
        }

        if (anoPubli.isEmpty()) {
            anoPubliEditText.error = "Ano de publicação é obrigatório"
            isValid = false
        } else if (!anoPubli.matches(Regex("\\d{4}"))) {
            anoPubliEditText.error = "Ano de publicação inválido"
            isValid = false
        }

        if (genero.isEmpty()) {
            generoEditText.error = "Gênero é obrigatório"
            isValid = false
        }

        if (sinopse.isEmpty()) {
            sinopseEditText.error = "Sinopse é obrigatória"
            isValid = false
        }

        return isValid
    }
}
