package ru.mireadev.igym.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Запрос на авторизацию")
data class LoginRequest(
    @field:Schema(description = "Email пользователя", example = "user@example.com", required = true)
    val email: String,

    @field:Schema(description = "Пароль", example = "Qwerty123!", required = true)
    val password: String
)
