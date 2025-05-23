package ru.mireadev.igym.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Ответ после успешной регистрации")
data class RegisterResponse(

    @field:Schema(description = "ID пользователя", example = "123")
    val userID: Long,

    @field:Schema(description = "Имя пользователя", example = "john_doe")
    val username: String,

    @field:Schema(description = "Email", example = "user@example.com")
    val email: String,

    @field:Schema(description = "Полное имя", example = "Иван Иванов")
    val fullName: String
)