package com.example.bookhive.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookhive.R
import com.example.bookhive.objects.Autor

class AutoresAdapter(private val context: Context, private val autores: MutableList<Autor>) : RecyclerView.Adapter<AutoresAdapter.AutorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_autor, parent, false)
        return AutorViewHolder(view)
    }

    override fun onBindViewHolder(holder: AutorViewHolder, position: Int) {
        val autor = autores[position]
        holder.bind(autor)
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
}
