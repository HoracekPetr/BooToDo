package com.example.todo.task.model.request

sealed interface TaskRequest {
    data class CreateTaskRequest(
            val title: String?,
            val description: String?,
            val priority: String?
    ) : TaskRequest

    data class UpdateTaskRequest(
            val title: String? = null,
            val description: String? = null,
            val isOpen: Boolean? = null,
            val priority: String? = null
    ): TaskRequest
}