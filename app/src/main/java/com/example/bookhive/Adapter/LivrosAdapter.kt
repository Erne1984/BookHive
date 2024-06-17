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
import com.example.bookhive.activities.EditLivroActivity
import com.example.bookhive.activities.ListLivros
import com.example.bookhive.activities.ListLivros.Companion.EDIT_LIVRO_REQUEST_CODE
import com.example.bookhive.objects.Livro
import org.json.JSONArray

class LivrosAdapter(private val context: Context, private val livros: MutableList<Livro>) : RecyclerView.Adapter<LivrosAdapter.LivroViewHolder>() {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("livros_prefs", Context.MODE_PRIVATE)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LivroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_livro, parent, false)
        return LivroViewHolder(view)
    }

    override fun onBindViewHolder(holder: LivroViewHolder, position: Int) {
        val livro = livros[position]
        holder.bind(livro)

        holder.btnDelete.setOnClickListener {
            deleteLivro(position)
        }

        holder.btnEdit.setOnClickListener {
            val intent = Intent(context, EditLivroActivity::class.java)
            intent.putExtra("livro_id", position)
            intent.putExtra("livro_titulo", livro.title)
            intent.putExtra("livro_autor", livro.author)
            intent.putExtra("livro_anoPubli", livro.anoPubli)
            intent.putExtra("livro_genero", livro.genero)
            intent.putExtra("livro_sinopse", livro.sinopse)
            (context as ListLivros).startActivityForResult(intent, EDIT_LIVRO_REQUEST_CODE)
        }
    }

    override fun getItemCount(): Int {
        return livros.size
    }

    inner class LivroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTitulo: TextView = itemView.findViewById(R.id.txtTitulo)
        private val txtAutor: TextView = itemView.findViewById(R.id.txtAutor)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
        val btnEdit: ImageButton = itemView.findViewById(R.id.btnEdit)
        fun bind(livro: Livro) {
            txtTitulo.text = livro.title
            txtAutor.text = livro.author
        }
    }

    private fun deleteLivro(position: Int) {
        livros.removeAt(position)
        notifyItemRemoved(position)
        saveLivros()
    }

    private fun saveLivros() {
        val editor = sharedPreferences.edit()
        val jsonArray = JSONArray()
        for (livro in livros) {
            jsonArray.put(livro.toJsonString())
        }
        editor.putString("livros", jsonArray.toString())
        editor.apply()
    }
}
