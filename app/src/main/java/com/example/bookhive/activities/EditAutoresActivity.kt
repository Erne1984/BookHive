package com.example.bookhive.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.bookhive.R

class EditAutoresActivity : AppCompatActivity() {

    private lateinit var editNome: EditText
    private lateinit var editNacionalidade: EditText
    private lateinit var editDataNascimento: EditText
    private lateinit var editBiografia: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_autores)

        editNome = findViewById(R.id.editNome)
        editNacionalidade = findViewById(R.id.editNacionalidade)
        editDataNascimento = findViewById(R.id.editDataNascimento)
        editBiografia = findViewById(R.id.editBiografia)
        btnSave = findViewById(R.id.btnSave)

        val autorId = intent.getIntExtra("autor_id", -1)
        val nome = intent.getStringExtra("autor_nome")
        val nacionalidade = intent.getStringExtra("autor_nacionalidade")
        val dataNascimento = intent.getStringExtra("autor_dataNascimento")
        val biografia = intent.getStringExtra("autor_biografia")

        val navigateListAuthor: Button = findViewById(R.id.navigateListAuthor)

        editNome.setText(nome)
        editNacionalidade.setText(nacionalidade)
        editDataNascimento.setText(dataNascimento)
        editBiografia.setText(biografia)

        btnSave.setOnClickListener {
            val nome = editNome.text.toString().trim()
            val nacionalidade = editNacionalidade.text.toString().trim()
            val dataNascimento = editDataNascimento.text.toString().trim()
            val biografia = editBiografia.text.toString().trim()

            val isValid = validateInputs(nome, nacionalidade, dataNascimento, biografia)

            if (isValid) {
                val resultIntent = Intent()
                resultIntent.putExtra("autor_id", autorId)
                resultIntent.putExtra("autor_nome", nome)
                resultIntent.putExtra("autor_nacionalidade", nacionalidade)
                resultIntent.putExtra("autor_dataNascimento", dataNascimento)
                resultIntent.putExtra("autor_biografia", biografia)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }

        navigateListAuthor.setOnClickListener{
            val intent = Intent(this, ListAutoresActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun validateInputs(nome: String, nacionalidade: String, dataNascimento: String, biografia: String): Boolean {
        var isValid = true

        if (nome.isEmpty()) {
            editNome.error = "Nome é obrigatório"
            isValid = false
        }

        if (nacionalidade.isEmpty()) {
            editNacionalidade.error = "Nacionalidade é obrigatória"
            isValid = false
        }

        if (dataNascimento.isEmpty()) {
            editDataNascimento.error = "Data de Nascimento é obrigatória"
            isValid = false
        } else if (!dataNascimento.matches(Regex("\\d{2}/\\d{2}/\\d{4}"))) {
            editDataNascimento.error = "Formato de data deve ser DD/MM/AAAA"
            isValid = false
        }

        if (biografia.isEmpty()) {
            editBiografia.error = "Biografia é obrigatória"
            isValid = false
        }

        return isValid
    }
}
