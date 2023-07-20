package com.example.todo.task.util.validator

import com.example.todo.task.model.Priority
import java.lang.IllegalArgumentException

fun isValidPriority(priority: String?): Boolean {
    return try {
        priority?.let {
            val existingPriority = Priority.valueOf(priority)
            existingPriority.toString().isNotBlank()
        } ?: false
    } catch (e: IllegalArgumentException) {
        false
    }
}