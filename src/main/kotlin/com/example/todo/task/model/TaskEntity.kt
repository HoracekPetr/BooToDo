package com.example.todo.task.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("task")
data class TaskEntity(
        @Id
        val id: Long = 0L,
        val title: String,
        val description: String,
        val priority: String,
        @Column("isopen")
        val isOpen: Boolean = true
)

fun TaskEntity.toDto() = TaskDto(
        id = id,
        title = title,
        description = description,
        isOpen = isOpen,
        priority = Priority.valueOf(priority)
)

fun List<TaskEntity>.toDto() = map { it.toDto() }
