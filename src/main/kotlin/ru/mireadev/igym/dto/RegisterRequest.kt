package ru.mireadev.igym.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import java.time.LocalDate

@Schema(description = "Запрос на регистрацию пользователя")
data class RegisterRequest(
    @field:Schema(
        description = "Уникальное имя пользователя", example = "john_doe", required = true)
    val username: String,

    @field:Schema(description = "Email пользователя", example = "user@example.com", required = true)
    @field:Email
    val email: String,

    @field:Schema(description = "Пароль", example = "SecurePass123!", required = true)
    val password: String,

    @field:Schema(description = "Полное имя", example = "Иван Иванов", required = true)
    val fullName: String,

    @field:Schema(description = "Краткая биография", example = "Люблю спорт и программирование", nullable = true)
    val bio: String? = null,

    @field:Schema(description = "Дата рождения", example = "1990-01-01", nullable = true, type = "string", format = "date")
    val dateOfBirth: LocalDate? = null
)