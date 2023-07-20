package com.example.todo.task.util.validator

import com.example.todo.task.model.TaskEntity
import com.example.todo.task.util.messages.TaskErrorMessages.errorTaskExistence

class UpdateTaskValidator {
    fun validateRequest(id: Long, task: TaskEntity?): UpdateTaskValidation {
        return when(task){
            null -> UpdateTaskValidation.INVALID(errorTaskExistence(id))
            else -> UpdateTaskValidation.SUCCESS(task = task)
        }
    }
}

sealed interface UpdateTaskValidation {
    val message: String?

    data class INVALID(override val message: String?) : UpdateTaskValidation
    data class SUCCESS(override val message: String? = null, val task: TaskEntity) : UpdateTaskValidation
}