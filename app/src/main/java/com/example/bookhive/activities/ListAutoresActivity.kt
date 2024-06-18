package com.example.bookhive.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookhive.R
import com.example.bookhive.adapters.AutoresAdapter
import com.example.bookhive.objects.Autor
import org.json.JSONArray

class ListAutoresActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var autoresList: MutableList<Autor>
    private lateinit var autoresRecyclerView: RecyclerView
    private lateinit var autoresAdapter: AutoresAdapter

    companion object {
        const val EDIT_AUTOR_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_autores)

        val navigateHomeAuthor: Button = findViewById(R.id.navigateHomeAutores)

        sharedPreferences = getSharedPreferences("autores_prefs", Context.MODE_PRIVATE)
        autoresList = mutableListOf()
        autoresRecyclerView = findViewById(R.id.recyclerViewAutores)
        autoresAdapter = AutoresAdapter(this, autoresList)

        autoresRecyclerView.layoutManager = LinearLayoutManager(this)
        autoresRecyclerView.adapter = autoresAdapter

        recuperarAutores()

        navigateHomeAuthor.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun recuperarAutores() {
        val jsonArrayString = sharedPreferences.getString("autores", "[]")
        val jsonArray = JSONArray(jsonArrayString)
        for (i in 0 until jsonArray.length()) {
            val jsonString = jsonArray.getString(i)
            val autor = Autor.fromJsonString(jsonString)
            autoresList.add(autor)
        }
        autoresAdapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_AUTOR_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.let {
                val autorId = it.getIntExtra("autor_id", -1)
                val nome = it.getStringExtra("autor_nome") ?: ""
                val nacionalidade = it.getStringExtra("autor_nacionalidade") ?: ""
                val dataNascimento = it.getStringExtra("autor_dataNascimento") ?: ""
                val biografia = it.getStringExtra("autor_biografia") ?: ""

                if (autorId != -1) {
                    val autor = autoresList[autorId]
                    autor.nome = nome
                    autor.nacionalidade = nacionalidade
                    autor.dataNascimento = dataNascimento
                    autor.biografia = biografia
                    autoresAdapter.notifyItemChanged(autorId)
                    saveAutores()
                }
            }
        }
    }


    private fun saveAutores() {
        val editor = sharedPreferences.edit()
        val jsonArray = JSONArray()
        for (autor in autoresList) {
            jsonArray.put(autor.toJsonString())
        }
        editor.putString("autores", jsonArray.toString())
        editor.apply()
    }
}
