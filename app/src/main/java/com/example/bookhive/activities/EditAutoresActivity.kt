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

        editNome.setText(nome)
        editNacionalidade.setText(nacionalidade)
        editDataNascimento.setText(dataNascimento)
        editBiografia.setText(biografia)

        btnSave.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("autor_id", autorId)
            resultIntent.putExtra("autor_nome", editNome.text.toString())
            resultIntent.putExtra("autor_nacionalidade", editNacionalidade.text.toString())
            resultIntent.putExtra("autor_dataNascimento", editDataNascimento.text.toString())
            resultIntent.putExtra("autor_biografia", editBiografia.text.toString())
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
