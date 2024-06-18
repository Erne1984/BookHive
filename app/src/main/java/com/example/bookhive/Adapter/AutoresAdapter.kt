package com.example.bookhive.adapters

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookhive.R
import com.example.bookhive.activities.EditAutoresActivity
import com.example.bookhive.activities.ListAutoresActivity
import com.example.bookhive.objects.Autor
import org.json.JSONArray

class AutoresAdapter(private val context: Context, private val autores: MutableList<Autor>) : RecyclerView.Adapter<AutoresAdapter.AutorViewHolder>() {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("autores_prefs", Context.MODE_PRIVATE)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_autor, parent, false)
        return AutorViewHolder(view)
    }

    override fun onBindViewHolder(holder: AutorViewHolder, position: Int) {
        val autor = autores[position]
        holder.bind(autor)

        holder.btnDelete.setOnClickListener {
            deleteAutor(position)
        }

        holder.btnEdit.setOnClickListener {
            val intent = Intent(context, EditAutoresActivity::class.java)
            intent.putExtra("autor_id", position)
            intent.putExtra("autor_nome", autor.nome)
            intent.putExtra("autor_nacionalidade", autor.nacionalidade)
            intent.putExtra("autor_dataNascimento", autor.dataNascimento)
            intent.putExtra("autor_biografia", autor.biografia)
            (context as ListAutoresActivity).startActivityForResult(intent, ListAutoresActivity.EDIT_AUTOR_REQUEST_CODE)
        }
    }

    override fun getItemCount(): Int {
        return autores.size
    }

    inner class AutorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtNome: TextView = itemView.findViewById(R.id.txtNome)
        private val txtNacionalidade: TextView = itemView.findViewById(R.id.txtNacionalidade)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
        val btnEdit: ImageButton = itemView.findViewById(R.id.btnEdit)

        fun bind(autor: Autor) {
            txtNome.text = autor.nome
            txtNacionalidade.text = autor.nacionalidade
        }
    }

    private fun deleteAutor(position: Int) {
        autores.removeAt(position)
        notifyItemRemoved(position)
        saveAutores()
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
