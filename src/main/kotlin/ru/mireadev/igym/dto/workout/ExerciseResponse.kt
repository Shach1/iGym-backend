package ru.mireadev.igym.dto.workout

import ru.mireadev.igym.entity.workout.Exercise

data class ExerciseResponse(
    val id: Long,
    val name: String,
    val description: String
)

fun Exercise.toResponse() = ExerciseResponse(
    id = id,
    name = name,
    description = description
)