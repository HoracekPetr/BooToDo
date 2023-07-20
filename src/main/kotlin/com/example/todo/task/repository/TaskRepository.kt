package com.example.todo.task.repository

import com.example.todo.task.model.TaskEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface TaskRepository : CoroutineCrudRepository<TaskEntity, Long>