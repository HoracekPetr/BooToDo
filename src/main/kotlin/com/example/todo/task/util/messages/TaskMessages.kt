package com.example.todo.task.util.messages

object TaskErrorMessages {
    const val ERROR_NO_TASKS = "You have no tasks."
    const val ERROR_EMPTY_TITLE = "Title can't be empty!"
    const val ERROR_EMPTY_DESCRIPTION = "Description can't be empty!"
    const val ERROR_EMPTY_PRIORITY = "Priority can't be empty!"

    fun errorPriorityValue(arg: Any?) = "Invalid priority value: $arg"
    fun errorTaskExistence(arg: Any?) = "Task with ID $arg does not exist!"
}

object TaskSuccessMessages {
    const val SUCCESS_CREATE_TASK = "Task created successfully."
    const val SUCCESS_UPDATE_TASK = "Task updated successfully."
    const val SUCCESS_DELETE_TASK = "Task deleted successfully"
}