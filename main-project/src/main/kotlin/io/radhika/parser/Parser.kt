package io.radhika.parser

import io.radhika.model.User

interface Parser {
    fun parse(jsonInput: String): User
}