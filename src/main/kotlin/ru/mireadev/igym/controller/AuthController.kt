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
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.mireadev.igym.dto.AuthResponse
import ru.mireadev.igym.dto.LoginRequest
import ru.mireadev.igym.dto.RegisterRequest
import ru.mireadev.igym.dto.RegisterResponse
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
                responseCode = "400",
                description = "Некорректные входные данные",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ErrorResponse::class))
                ]),
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
    ): RegisterResponse {
        return userService.register(request)
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
        return try {
            val authResponse = authService.authenticate(request)
            ResponseEntity.ok(authResponse)
        } catch (e: BadCredentialsException) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponse(
                    success = false,
                    message = e.message!!,
                    errorCode = "AUTH_FAILED"
                ))
        }
    }
}