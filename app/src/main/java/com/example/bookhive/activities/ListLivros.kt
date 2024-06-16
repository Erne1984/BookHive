package com.example.bookhive.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_livros)

        sharedPreferences = getSharedPreferences("livros_prefs", Context.MODE_PRIVATE)
        livrosList = mutableListOf()
        livrosRecyclerView = findViewById(R.id.recyclerViewLivros)
        livrosAdapter = LivrosAdapter(livrosList)

        livrosRecyclerView.layoutManager = LinearLayoutManager(this)
        livrosRecyclerView.adapter = livrosAdapter

        recuperarLivros()
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
}
