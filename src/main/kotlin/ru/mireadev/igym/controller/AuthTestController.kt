package ru.mireadev.igym.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/test-auth")
class AuthTestController {

    // Открытый эндпоинт (доступен без авторизации)
    @GetMapping("/public")
    fun publicEndpoint(): String {
        return "Это открытый эндпоинт, доступен всем"
    }

    // Защищенный эндпоинт (требует авторизации)
    @GetMapping("/protected")
    @PreAuthorize("isAuthenticated()")
    fun protectedEndpoint(): String {
        return "Это защищенный эндпоинт. Вы авторизованы!"
    }
}