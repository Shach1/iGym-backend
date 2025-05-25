package ru.mireadev.igym.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "Ответ с рекомендацией")
data class RecommendationResponse(
    @field:Schema(
        description = "ID рекомендации",
        example = "3"
    )
    val id: Int,

    @field:Schema(
        description = "Заголовок рекомендации",
        example = "Кардио для похудения"
    )
    val title: String,

    @field:Schema(
        description = "Содержание рекомендации",
        example = "Бег, велоспорт или плавание 3-4 раза в неделю по 30-45 минут..."
    )
    val content: String,

    @field:Schema(
        description = "Дата создания",
        example = "2025-05-25T17:44:45"
    )
    val createdAt: LocalDateTime
)