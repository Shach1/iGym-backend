package ru.mireadev.igym.dto

import io.swagger.v3.oas.annotations.media.Schema
import ru.mireadev.igym.entity.Sex
import java.time.LocalDate

data class ProfileResponse(

    @field:Schema(description = "Id пользователя", example = "1")
    val userId: Long,

    @field:Schema(description = "Пол", example = "MALE")
    val sex: Sex,

    @field:Schema(description = "Дата рождения", example = "1990-01-01")
    val birthDate: LocalDate,

    @field:Schema(description = "Рост (см)", example = "175")
    val height: Int,

    @field:Schema(description = "Вес (кг)", example = "70.5")
    val weight: Double,

    @field:Schema(description = "Возраст", example = "33")
    val age: Int
)
