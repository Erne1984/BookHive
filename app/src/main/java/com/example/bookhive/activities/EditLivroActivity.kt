package com.example.bookhive.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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

        // Recebe os dados do livro
        val livroId = intent.getIntExtra("livro_id", -1)
        val livroTitulo = intent.getStringExtra("livro_titulo")
        val livroAutor = intent.getStringExtra("livro_autor")
        val livroAnoPubli = intent.getStringExtra("livro_anoPubli")
        val livroGenero = intent.getStringExtra("livro_genero")
        val livroSinopse = intent.getStringExtra("livro_sinopse")

        // Preenche os campos com os dados do livro
        tituloEditText.setText(livroTitulo)
        autorEditText.setText(livroAutor)
        anoPubliEditText.setText(livroAnoPubli)
        generoEditText.setText(livroGenero)
        sinopseEditText.setText(livroSinopse)

        saveButton.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("livro_id", livroId)
            resultIntent.putExtra("livro_titulo", tituloEditText.text.toString())
            resultIntent.putExtra("livro_autor", autorEditText.text.toString())
            resultIntent.putExtra("livro_anoPubli", anoPubliEditText.text.toString())
            resultIntent.putExtra("livro_genero", generoEditText.text.toString())
            resultIntent.putExtra("livro_sinopse", sinopseEditText.text.toString())
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
