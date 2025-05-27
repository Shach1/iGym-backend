package ru.mireadev.igym.dto.workout

import ru.mireadev.igym.entity.workout.MuscleGroup

data class MuscleGroupResponse(
    val id: Long,
    val name: String,
    val description: String?
)
fun MuscleGroup.toResponse() = MuscleGroupResponse(
    id = id,
    name = name,
    description = description
)