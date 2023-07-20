package com.example.todo.task.util.validator

import com.example.todo.task.model.TaskEntity
import com.example.todo.task.util.messages.TaskErrorMessages.ERROR_NO_TASKS
import com.example.todo.task.util.messages.TaskErrorMessages.errorTaskExistence

class GetTaskValidator {
    fun validateGetTasksRequest(data: List<TaskEntity>?): GetTasksValidation {
        return when {
            !data.isNullOrEmpty() -> GetTasksValidation.SUCCESS(
                    data = data
            )

            else -> GetTasksValidation.EMPTY(
                    message = ERROR_NO_TASKS
            )
        }
    }

    fun validateGetTaskByIdRequest(id: Long,task: TaskEntity?): GetTaskByIdValidation {
        return when (task) {
            null -> GetTaskByIdValidation.INVALID(errorTaskExistence(id))
            else -> GetTaskByIdValidation.SUCCESS(data = task)
        }
    }
}

sealed interface GetTasksValidation {
    val message: String?

    data class EMPTY(override val message: String?) : GetTasksValidation
    data class SUCCESS(
            override val message: String? = null,
            val data: List<TaskEntity>
    ) : GetTasksValidation
}

sealed interface GetTaskByIdValidation {
    val message: String?

    data class INVALID(override val message: String?) : GetTaskByIdValidation
    data class SUCCESS(
            override val message: String? = null,
            val data: TaskEntity
    ) : GetTaskByIdValidation
}