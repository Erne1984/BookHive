package com.example.bookhive.objects

import org.json.JSONObject

data class Autor(
    var nome: String,
    var nacionalidade: String,
    var dataNascimento: String,
    var biografia: String
) {
    fun toJsonString(): String {
        val jsonObject = JSONObject()
        jsonObject.put("nome", nome)
        jsonObject.put("nacionalidade", nacionalidade)
        jsonObject.put("dataNascimento", dataNascimento)
        jsonObject.put("biografia", biografia)
        return jsonObject.toString()
    }

    companion object {
        fun fromJsonString(jsonString: String): Autor {
            val jsonObject = JSONObject(jsonString)
            return Autor(
                jsonObject.getString("nome"),
                jsonObject.getString("nacionalidade"),
                jsonObject.getString("dataNascimento"),
                jsonObject.getString("biografia")
            )
        }
    }
}
