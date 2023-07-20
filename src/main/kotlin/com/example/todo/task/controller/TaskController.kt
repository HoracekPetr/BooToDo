package com.example.todo.task.controller

import com.example.todo.core.error.GeneralErrorMessages.ERROR_UNEXPECTED
import com.example.todo.core.response.GeneralApiResponse
import com.example.todo.task.model.MockTaskDto
import com.example.todo.task.model.TaskDto
import com.example.todo.task.model.request.TaskRequest
import com.example.todo.task.service.TaskService
import com.example.todo.task.util.messages.TaskSuccessMessages.SUCCESS_CREATE_TASK
import com.example.todo.task.util.messages.TaskSuccessMessages.SUCCESS_DELETE_TASK
import com.example.todo.task.util.messages.TaskSuccessMessages.SUCCESS_UPDATE_TASK
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/task")
class TaskController(
        private val service: TaskService
) {

    @GetMapping
    suspend fun getAllTasks(): ResponseEntity<GeneralApiResponse<List<TaskDto>?>> = ResponseEntity(
            GeneralApiResponse(data = service.getAllTasks()), HttpStatus.OK
    )

    @GetMapping("/{id}")
    suspend fun getTaskById(@PathVariable id: Long): ResponseEntity<GeneralApiResponse<TaskDto>?> = ResponseEntity(
            GeneralApiResponse(data = service.getTaskById(id)), HttpStatus.OK
    )

    @PostMapping
    suspend fun createTask(@RequestBody body: TaskRequest.CreateTaskRequest): ResponseEntity<GeneralApiResponse<Unit>> {
        return when (service.createTask(request = body)) {
            true -> ResponseEntity(GeneralApiResponse(message = SUCCESS_CREATE_TASK), HttpStatus.OK)
            false -> ResponseEntity(GeneralApiResponse(message = ERROR_UNEXPECTED), HttpStatus.BAD_REQUEST)
        }
    }

    @PatchMapping("/{id}")
    suspend fun updateTask(
            @PathVariable id: Long,
            @RequestBody body: TaskRequest.UpdateTaskRequest
    ): ResponseEntity<GeneralApiResponse<Unit>> {
        return when (service.updateTask(id, body)) {
            true -> ResponseEntity(GeneralApiResponse(message = SUCCESS_UPDATE_TASK), HttpStatus.OK)
            false -> ResponseEntity(GeneralApiResponse(message = ERROR_UNEXPECTED), HttpStatus.BAD_REQUEST)
        }
    }

    @DeleteMapping("/{id}")
    suspend fun deleteTask(@PathVariable id: Long): ResponseEntity<GeneralApiResponse<Unit>> {
        return when (service.deleteTask(id)) {
            true -> ResponseEntity(GeneralApiResponse(message = SUCCESS_DELETE_TASK), HttpStatus.OK)
            false -> ResponseEntity(GeneralApiResponse(message = ERROR_UNEXPECTED), HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/mocks")
    suspend fun getMockTasks(
            @RequestParam(required = false, defaultValue = "") userId: String
    ): ResponseEntity<GeneralApiResponse<List<MockTaskDto>?>> = ResponseEntity(
            GeneralApiResponse(data = service.getTasksFromMockApi(userId)), HttpStatus.OK
    )
}