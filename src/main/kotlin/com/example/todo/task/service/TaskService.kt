package com.example.todo.task.service

import com.example.todo.task.model.TaskEntity
import com.example.todo.task.model.request.TaskRequest
import com.example.todo.task.repository.TaskRepository
import com.example.todo.core.error.GeneralAppException
import com.example.todo.task.model.TaskDto
import com.example.todo.task.model.toDto
import com.example.todo.task.util.validator.*
import kotlinx.coroutines.flow.toList
import org.springframework.beans.BeanWrapperImpl
import org.springframework.stereotype.Service

@Service
class TaskService(
        private val repository: TaskRepository
) {

    suspend fun getAllTasks(): List<TaskDto> {
        val validationResult = GetTaskValidator().validateGetTasksRequest(
                data = repository.findAll().toList()
        )
        return when (validationResult) {
            is GetTasksValidation.SUCCESS -> validationResult.data.toDto().sortedBy { it.id }
            else -> {
                throw GeneralAppException(message = validationResult.message)
            }
        }
    }

    suspend fun getTaskById(id: Long): TaskDto {
        val validationResult = GetTaskValidator().validateGetTaskByIdRequest(
                id = id,
                task = repository.findById(id)
        )
        return when (validationResult) {
            is GetTaskByIdValidation.SUCCESS -> validationResult.data.toDto()
            else -> {
                throw GeneralAppException(message = validationResult.message)
            }
        }
    }

    suspend fun createTask(request: TaskRequest.CreateTaskRequest): Boolean {
        return when (val validationResult = CreateTaskValidator().validateRequest(request)) {
            is CreateTaskValidation.SUCCESS -> {
                repository.save(
                        TaskEntity(
                                title = validationResult.title,
                                description = validationResult.description,
                                priority = validationResult.priority
                        )
                )
                true
            }

            else -> {
                throw GeneralAppException(message = validationResult.message)
            }
        }
    }

    suspend fun updateTask(id: Long, request: TaskRequest.UpdateTaskRequest): Boolean {
        return when (val validationResult = UpdateTaskValidator().validateRequest(id = id, task = repository.findById(id))) {
            is UpdateTaskValidation.SUCCESS -> {
                val task = validationResult.task
                val updatedTask = task.copy(
                        title = request.title ?: task.title,
                        description = request.description ?: task.description,
                        isOpen = request.isOpen ?: task.isOpen,
                        priority = request.priority ?: task.priority
                )
                repository.save(updatedTask)
                true
            }

            else -> {
                throw GeneralAppException(message = validationResult.message)
            }
        }
    }

    suspend fun deleteTask(id: Long): Boolean {
        val validationResult = GetTaskValidator().validateGetTaskByIdRequest(
                id = id,
                task = repository.findById(id)
        )
        return when (validationResult) {
            is GetTaskByIdValidation.SUCCESS -> {
                repository.delete(validationResult.data)
                true
            }
            else -> {
                throw GeneralAppException(message = validationResult.message)
            }
        }
    }
}