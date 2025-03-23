package io.radhika.parser

import com.google.gson.Gson
import io.radhika.model.User

class RTMP : Parser {
    override fun parse(jsonInput: String): User {
        return try {
            gson.fromJson(jsonInput, User::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            User()
        }
    }

    companion object {
        val gson by lazy { Gson() }
    }
}