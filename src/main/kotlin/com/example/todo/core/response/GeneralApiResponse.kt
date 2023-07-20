package com.example.todo.core.response

data class GeneralApiResponse<T>(
        val message: String? = null,
        val data: T? = null
)
