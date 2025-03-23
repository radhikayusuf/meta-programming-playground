package io.radhika.parser

import io.radhika.model.User
import org.json.JSONObject

class Manual : Parser{
    private val jsonObj = JSONObject("{}")

    override fun parse(jsonInput: String): User {
        val jsonObject = JSONObject(jsonInput)
        return User(
            jsonObject.getString("first_name"),
            jsonObject.getString("last_name"),
            jsonObject.getInt("age"),
            jsonObject.getString("phone_number"),
        )
    }
}