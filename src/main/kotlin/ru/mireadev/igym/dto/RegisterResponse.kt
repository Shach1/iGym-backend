package ru.mireadev.igym.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Schema(description = "Ответ после успешной регистрации")
data class RegisterResponse(

    @field:Schema(description = "ID пользователя", example = "123")
    val userID: Long,

    @field:Schema(description = "Имя пользователя", example = "john_doe")
    val username: String,

    @field:Schema(description = "Email", example = "user@example.com")
    val email: String,

    @field:Schema(description = "Полное имя", example = "Иван Иванов")
    val fullName: String,

    @field:Schema(description = "Биография", example = "Люблю спорт и программирование", nullable = true)
    val bio: String?,

    @field:Schema(description = "Дата рождения", example = "1990-01-01", nullable = true, type = "string", format = "date")
    val dateOfBirth: LocalDate?
)