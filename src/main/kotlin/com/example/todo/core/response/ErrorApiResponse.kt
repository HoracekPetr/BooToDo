package com.example.todo.core.response

import org.springframework.http.HttpStatus

data class ErrorApiResponse(
        val code: Int? = null,
        val message: String? = null,
)
