package io.radhika.parser

import io.radhika.model.User
import io.radhika.model.fromJson

class CTMP : Parser {
    override fun parse(jsonInput: String): User {
        return User.fromJson(jsonInput)
    }
}