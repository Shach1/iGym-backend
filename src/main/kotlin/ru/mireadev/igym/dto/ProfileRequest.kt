package ru.mireadev.igym.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Past
import ru.mireadev.igym.entity.Sex
import java.time.LocalDate

@Schema(description = "Запрос на обновление профиля")
data class ProfileRequest(
    @field:Schema(
        description = "ID пользователя",
        example = "123",
        required = true
    )
    val userId: Long,

    @field:Schema(
        description = "Пол пользователя",
        example = "MALE",
        allowableValues = ["MALE", "FEMALE"],
        required = true
    )
    val sex: Sex,

    @field:Schema(
        description = "Дата рождения (формат: YYYY-MM-DD)",
        example = "1990-01-01",
        required = true
    )@field:Past
    val birthDate: LocalDate,

    @field:Schema(
        description = "Рост в см",
        example = "175",
        minimum = "100",
        maximum = "250",
        required = true
    )
    @field:Min(100) @field:Max(250)
    val height: Int,

    @field:Schema(
        description = "Вес в кг",
        example = "70.5",
        minimum = "30.0",
        maximum = "300.0",
        required = true
    )
    @field:Min(30) @field:Max(300)
    val weight: Double
)
