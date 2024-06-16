package com.example.bookhive.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookhive.R
import com.example.bookhive.objects.Livro

class LivrosAdapter(private val livros: List<Livro>) : RecyclerView.Adapter<LivrosAdapter.LivroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LivroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_livro, parent, false)
        return LivroViewHolder(view)
    }

    override fun onBindViewHolder(holder: LivroViewHolder, position: Int) {
        val livro = livros[position]
        holder.bind(livro)
    }

    override fun getItemCount(): Int {
        return livros.size
    }

    inner class LivroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTitulo: TextView = itemView.findViewById(R.id.txtTitulo)
        private val txtAutor: TextView = itemView.findViewById(R.id.txtAutor)

        fun bind(livro: Livro) {
            txtTitulo.text = livro.title
            txtAutor.text = livro.author
        }
    }
}
