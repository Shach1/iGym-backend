package ru.mireadev.igym.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Ответ с токеном авторизации")
data class AuthResponse(
    @field:Schema(description = "ID пользователя", example = "123")
    val userId: Long,

    @field:Schema(description = "JWT токен", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    val token: String,

    @field:Schema(description = "Email пользователя", example = "user@example.com")
    val email: String,

    @field:Schema(description = "Имя пользователя", example = "Иван Иванов")
    val username: String,

    @field:Schema(description = "Полное имя", example = "Иван Иванов")
    val fullName: String
)
