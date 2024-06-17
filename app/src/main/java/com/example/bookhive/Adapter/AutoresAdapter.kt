package com.example.bookhive.Adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookhive.adapters.LivrosAdapter
import com.example.bookhive.objects.Autor
import com.example.bookhive.objects.Livro
import org.json.JSONArray

class AutoresAdapter(private val context: Context, private val autores: MutableList<Autor>) : RecyclerView.Adapter<LivrosAdapter.LivroViewHolder>() {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("autores_prefs", Context.MODE_PRIVATE)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LivrosAdapter.LivroViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: LivrosAdapter.LivroViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    private fun saveAutores() {
        val editor = sharedPreferences.edit()
        val jsonArray = JSONArray()
        for (autor in autores) {
            jsonArray.put(autor.toJsonString())
        }
        editor.putString("autores", jsonArray.toString())
        editor.apply()
    }


}