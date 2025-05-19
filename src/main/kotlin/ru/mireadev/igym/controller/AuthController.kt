package ru.mireadev.igym.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.mireadev.igym.dto.*
import ru.mireadev.igym.service.AuthService
import ru.mireadev.igym.service.UserService

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Аутентификация и регистрация", description = "Эндпоинты для входа и регистрации")
class AuthController(
    private val userService: UserService,
    private val authService: AuthService
) {

    @Operation(
        summary = "Регистрация нового пользователя",
        description = "Создает нового пользователя в системе",
        responses = [
            ApiResponse(
                responseCode = "201",
                description = "Пользователь успешно зарегистрирован",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = RegisterResponse::class))
                ]),
            ApiResponse(
                responseCode = "403",
                description = "Некорректные входные данные"
            ),
            ApiResponse(
                responseCode = "409",
                description = "Пользователь уже существует",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ErrorResponse::class))
                ])
        ])
    @PostMapping("/register")
    fun register(
        @Parameter(description = "Данные для регистрации", required = true)
        @Valid @RequestBody request: RegisterRequest
    ): ResponseEntity<Any> {
        return when (val result = authService.registerUser(request)) {
            is AuthService.RegistrationResult.Success ->
                ResponseEntity.status(HttpStatus.CREATED).body(result.response)
            is AuthService.RegistrationResult.Conflict ->
                ResponseEntity.status(HttpStatus.CONFLICT).body(result.error)
            is AuthService.RegistrationResult.ValidationError ->
                ResponseEntity.badRequest().body(result.error)
        }
    }

    @Operation(
        summary = "Вход в систему",
        description = "Проверяет учетные данные и возвращает JWT токен",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Успешная авторизация",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = AuthResponse::class))
                ]),
            ApiResponse(
                responseCode = "401",
                description = "Неверные учетные данные",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ErrorResponse::class))
                ])
        ])
    @PostMapping("/login")
    fun login(
        @Parameter(description = "Данные для входа", required = true)
        @Valid @RequestBody request: LoginRequest
    ): ResponseEntity<Any> {
        return when (val result = authService.authenticate(request)) {
            is AuthService.AuthResult.Success ->
                ResponseEntity.ok(result.response)
            is AuthService.AuthResult.InvalidCredentials ->
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result.error)
        }
    }
}