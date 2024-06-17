package com.example.bookhive.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookhive.R
import com.example.bookhive.adapters.AutoresAdapter
import com.example.bookhive.objects.Autor
import org.json.JSONArray

class ListAutoresActivity : AppCompatActivity() {

    private lateinit var autoresList: MutableList<Autor>
    private lateinit var autoresRecyclerView: RecyclerView
    private lateinit var autoresAdapter: AutoresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_autores)

        autoresList = mutableListOf()
        autoresRecyclerView = findViewById(R.id.recyclerViewAutores)
        autoresAdapter = AutoresAdapter(this, autoresList)

        autoresRecyclerView.layoutManager = LinearLayoutManager(this)
        autoresRecyclerView.adapter = autoresAdapter

        recuperarAutores()
    }

    private fun recuperarAutores() {
        val sharedPreferences = getSharedPreferences("autores_prefs", MODE_PRIVATE)
        val jsonArrayString = sharedPreferences.getString("autores", "[]")
        val jsonArray = JSONArray(jsonArrayString)
        for (i in 0 until jsonArray.length()) {
            val jsonString = jsonArray.getString(i)
            val autor = Autor.fromJsonString(jsonString)
            autoresList.add(autor)
        }
        autoresAdapter.notifyDataSetChanged()
    }
}
