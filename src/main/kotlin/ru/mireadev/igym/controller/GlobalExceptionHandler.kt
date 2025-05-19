package ru.mireadev.igym.controller

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleBadCredentials(ex: BadCredentialsException): ErrorResponse {
        return ErrorResponse(
            success = false,
            message = "Неверный email или пароль",
            errorCode = "AUTH_FAILED"
        )
    }
}

@Schema(description = "Формат ошибки авторизации")
data class ErrorResponse(
    @field:Schema(description = "Флаг успешности", example = "false")
    val success: Boolean,

    @field:Schema(description = "Сообщение об ошибке", example = "Неверный email или пароль")
    val message: String,

    @field:Schema(description = "Код ошибки", example = "AUTH_FAILED")
    val errorCode: String
)