package ru.mireadev.igym.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.mireadev.igym.dto.ErrorResponse
import ru.mireadev.igym.dto.ProfileRequest
import ru.mireadev.igym.dto.ProfileResponse
import ru.mireadev.igym.service.ProfileService

@RestController
@RequestMapping("/api/profiles")
@Tag(name = "Профиль(доп инфа про пользователя)", description = "API для управления профилями пользователей")
class ProfileController(
    private val profileService: ProfileService
) {
    @Operation(
        summary = "Обновить или создать профиль",
        description = "Обновляет существующий профиль пользователя или создает новый, если не существует",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Профиль успешно обновлен/создан",
                content = [Content(schema = Schema(implementation = ProfileResponse::class))]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Неверные данные в запросе",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "Пользователь не найден",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    @PutMapping
    fun updateProfile(
        @RequestBody
        @Valid
        @Parameter(description = "Данные профиля для обновления", required = true)
        request: ProfileRequest
    ): ResponseEntity<Any> {
        return profileService.createOrUpdateProfile(request)
    }

    @Operation(
        summary = "Получить профиль пользователя",
        description = "Возвращает профиль пользователя по его ID",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Профиль найден",
                content = [Content(schema = Schema(implementation = ProfileResponse::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "Профиль не найден",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    @GetMapping("/{userId}")
    fun getProfile(
        @Parameter(description = "ID пользователя", example = "123", required = true)
        @PathVariable userId: Long
    ): ResponseEntity<Any> {
        return profileService.getProfile(userId)
    }
}