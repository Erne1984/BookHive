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
import com.example.bookhive.objects.Livro
import com.example.bookhive.adapters.LivrosAdapter
import org.json.JSONArray

class ListLivros : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var livrosList: MutableList<Livro>
    private lateinit var livrosRecyclerView: RecyclerView
    private lateinit var livrosAdapter: LivrosAdapter

    companion object {
        const val EDIT_LIVRO_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_livros)

        val navigateHome: Button = findViewById(R.id.navigateHome)

        sharedPreferences = getSharedPreferences("livros_prefs", Context.MODE_PRIVATE)
        livrosList = mutableListOf()
        livrosRecyclerView = findViewById(R.id.recyclerViewLivros)
        livrosAdapter = LivrosAdapter(this, livrosList)

        livrosRecyclerView.layoutManager = LinearLayoutManager(this)
        livrosRecyclerView.adapter = livrosAdapter

        recuperarLivros()

        navigateHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun recuperarLivros() {
        val jsonArrayString = sharedPreferences.getString("livros", "[]")
        val jsonArray = JSONArray(jsonArrayString)
        for (i in 0 until jsonArray.length()) {
            val jsonString = jsonArray.getString(i)
            val livro = Livro.fromJsonString(jsonString)
            livrosList.add(livro)
        }
        livrosAdapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_LIVRO_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.let {
                val livroId = it.getIntExtra("livro_id", -1)
                val titulo = it.getStringExtra("livro_titulo") ?: ""
                val autor = it.getStringExtra("livro_autor") ?: ""
                val anoPubli = it.getStringExtra("livro_anoPubli") ?: ""
                val genero = it.getStringExtra("livro_genero") ?: ""
                val sinopse = it.getStringExtra("livro_sinopse") ?: ""

                if (livroId != -1) {
                    val livro = livrosList[livroId]
                    livro.title = titulo
                    livro.author = autor
                    livro.anoPubli = anoPubli
                    livro.genero = genero
                    livro.sinopse = sinopse
                    livrosAdapter.notifyItemChanged(livroId)
                    saveLivros()
                }
            }
        }
    }

    private fun saveLivros() {
        val editor = sharedPreferences.edit()
        val jsonArray = JSONArray()
        for (livro in livrosList) {
            jsonArray.put(livro.toJsonString())
        }
        editor.putString("livros", jsonArray.toString())
        editor.apply()
    }
}
