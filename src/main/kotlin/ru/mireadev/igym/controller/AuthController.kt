package ru.mireadev.igym.controller

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.mireadev.igym.dto.AuthResponse
import ru.mireadev.igym.dto.LoginRequest
import ru.mireadev.igym.dto.RegisterRequest
import ru.mireadev.igym.dto.UserResponse
import ru.mireadev.igym.service.AuthService
import ru.mireadev.igym.service.UserService

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val userService: UserService,
    private val authService: AuthService
) {

    @PostMapping("/register")
    fun register(@Valid @RequestBody request: RegisterRequest): UserResponse {
        return userService.register(request)
    }
    @PostMapping("/login")
    fun login(@Valid @RequestBody request: LoginRequest): ResponseEntity<AuthResponse> {
        return ResponseEntity.ok(authService.authenticate(request))
    }
}