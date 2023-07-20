package com.example.todo.core.error

import com.example.todo.core.response.ErrorApiResponse
import com.example.todo.core.response.GeneralApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.server.handler.ResponseStatusExceptionHandler

@ControllerAdvice
class GeneralExceptionHandler : ResponseStatusExceptionHandler() {
    @ExceptionHandler(GeneralAppException::class)
    fun handleGeneralException(e: GeneralAppException): ResponseEntity<*> {
        return ResponseEntity(ErrorApiResponse(
                message = e.message,
                code = HttpStatus.BAD_REQUEST.value()
        ), HttpStatus.BAD_REQUEST)
    }
}