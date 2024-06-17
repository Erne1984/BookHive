package com.example.bookhive.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookhive.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnNavigateAddAuthor: Button = findViewById(R.id.navigate_add_author)
        val btnNavigateListAuthor: Button = findViewById(R.id.navigate_list_autores)

        val btnNavigateAddBook: Button = findViewById(R.id.navigate_add_book)
        val btnNavigateListLivros: Button = findViewById(R.id.navigate_list_livros)

        btnNavigateAddAuthor.setOnClickListener{
            val intent = Intent(this, AddAutoresActivity::class.java)
            startActivity(intent)
        }
        btnNavigateListAuthor.setOnClickListener{
            val intent = Intent(this, ListAutoresActivity::class.java)
            startActivity(intent)
        }

        btnNavigateAddBook.setOnClickListener {
            val intent = Intent(this, AddLivrosActivity::class.java)
            startActivity(intent)
        }
        btnNavigateListLivros.setOnClickListener{
            val intent = Intent(this, ListLivros::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.Search -> {
                Toast.makeText(this, "you clicked Search!", Toast.LENGTH_LONG).show()
                true
            }
            R.id.Favorite -> {
                Toast.makeText(this, "you clicked Favorite!", Toast.LENGTH_LONG).show()
                true
            }
            R.id.Share -> {
                Toast.makeText(this, "you clicked Share!", Toast.LENGTH_LONG).show()
                true
            }
            R.id.Whatsapp -> {
                Toast.makeText(this, "you clicked Whatsapp!", Toast.LENGTH_LONG).show()
                true
            }
            R.id.Instagram -> {
                Toast.makeText(this, "you clicked Instagram!", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}