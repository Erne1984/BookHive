package com.example.bookhive.objects

data class Livro(
    val id: String? = null,
    val titulo: String,
    val autor: String,
    val anoPubli: String,
    val genero: String,
    val sinopse: String
)