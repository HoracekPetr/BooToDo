package com.example.todo.task.model

data class TaskDto(
        val id: Long = 0L,
        val title: String,
        val description: String,
        val isOpen: Boolean,
        val priority: Priority
)

fun TaskDto.toEntity() = TaskEntity(
        id = id,
        title = title,
        description = description,
        isOpen = isOpen,
        priority = priority.name
)
