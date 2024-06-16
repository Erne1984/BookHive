package com.example.bookhive.objects

import org.json.JSONObject

data class Livro(
    val title: String,
    val author: String,
    val anoPubli: String,
    val genero: String,
    val sinopse: String
) {
    fun toJsonString(): String {
        val jsonObject = JSONObject()
        jsonObject.put("title", title)
        jsonObject.put("author", author)
        jsonObject.put("anoPubli", anoPubli)
        jsonObject.put("genero", genero)
        jsonObject.put("sinopse", sinopse)
        return jsonObject.toString()
    }

    companion object {
        fun fromJsonString(jsonString: String): Livro {
            val jsonObject = JSONObject(jsonString)
            return Livro(
                jsonObject.getString("title"),
                jsonObject.getString("author"),
                jsonObject.getString("anoPubli"),
                jsonObject.getString("genero"),
                jsonObject.getString("sinopse")
            )
        }
    }
}
