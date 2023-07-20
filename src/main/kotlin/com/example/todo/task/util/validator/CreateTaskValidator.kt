package com.example.todo.task.util.validator

import com.example.todo.task.model.request.TaskRequest
import com.example.todo.task.util.messages.TaskErrorMessages.ERROR_EMPTY_DESCRIPTION
import com.example.todo.task.util.messages.TaskErrorMessages.ERROR_EMPTY_PRIORITY
import com.example.todo.task.util.messages.TaskErrorMessages.ERROR_EMPTY_TITLE
import com.example.todo.task.util.messages.TaskErrorMessages.errorPriorityValue

class CreateTaskValidator {
    fun validateRequest(request: TaskRequest.CreateTaskRequest): CreateTaskValidation {
        return when {
            request.title.isNullOrBlank() -> CreateTaskValidation.EMPTY(ERROR_EMPTY_TITLE)
            request.description.isNullOrBlank() -> CreateTaskValidation.EMPTY(ERROR_EMPTY_DESCRIPTION)
            request.priority.isNullOrBlank() -> CreateTaskValidation.EMPTY(ERROR_EMPTY_PRIORITY)
            !isValidPriority(request.priority) -> CreateTaskValidation.INVALID(errorPriorityValue(request.priority))
            else -> CreateTaskValidation.SUCCESS(
                    title = request.title,
                    description = request.description,
                    priority = request.priority
            )
        }
    }
}

sealed interface CreateTaskValidation {
    val message: String?

    data class EMPTY(override val message: String?) : CreateTaskValidation
    data class INVALID(override val message: String?) : CreateTaskValidation
    data class SUCCESS(
            override val message: String? = null,
            val title: String,
            val description: String,
            val priority: String
    ) : CreateTaskValidation
}