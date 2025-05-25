package ru.mireadev.igym.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.mireadev.igym.dto.ErrorResponse
import ru.mireadev.igym.dto.RecommendationResponse
import ru.mireadev.igym.entity.Recommendation
import ru.mireadev.igym.entity.ReferenceCategory
import ru.mireadev.igym.service.ReferenceService

@RestController
@RequestMapping("/api/reference")
@Tag(name = "Справочник", description = "API для работы со справочником рекомендаций")
class ReferenceController(
    private val referenceService: ReferenceService,
) {
    @Operation(
        summary = "Получить все категории",
        description = "Возвращает список всех доступных категорий рекомендаций",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Список категорий",
                content = [Content(
                    mediaType = "application/json",
                    array = ArraySchema(schema = Schema(implementation = ReferenceCategory::class)))
                ]
            )
        ]
    )
    @GetMapping("/categories")
    fun getAllCategories(): List<ReferenceCategory> {
        return referenceService.getAllCategories()
    }


    @Operation(
        summary = "Получить рекомендации по ID категории",
        description = "Возвращает список рекомендаций для указанной категории по её ID",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Список рекомендаций",
                content = [Content(
                    mediaType = "application/json",
                    array = ArraySchema(schema = Schema(implementation = RecommendationResponse::class)))
                ]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Неверный ID категории",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    @GetMapping("/recommendations")
    fun getRecommendationsByCategoryId(
        @Parameter(description = "ID категории", example = "1", required = true)
        @RequestParam categoryId: Int
    ): ResponseEntity<List<RecommendationResponse>> {
        return ResponseEntity.ok(referenceService.getRecommendationsByCategoryId(categoryId))
    }
}