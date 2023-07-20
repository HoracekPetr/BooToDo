package com.example.todo.core.error

import org.springframework.http.HttpStatus

class GeneralAppException(
        override val message: String?,
        override val cause: Throwable? = null,
        val httpStatus: HttpStatus = HttpStatus.BAD_REQUEST
): RuntimeException(message)
