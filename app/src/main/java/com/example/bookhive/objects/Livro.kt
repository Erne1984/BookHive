package com.example.bookhive.objects

import org.json.JSONObject

data class Livro(
    var title: String,
    var author: String,
    var anoPubli: String,
    var genero: String,
    var sinopse: String
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
