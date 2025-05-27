package ru.mireadev.igym.dto.workout

import ru.mireadev.igym.entity.workout.Workout

data class WorkoutShortResponse(
    val id: Long,
    val title: String,
    val description: String,
    val muscleGroup: String
)

fun Workout.toShortResponse() = WorkoutShortResponse(
    id = id,
    title = title,
    description = description,
    muscleGroup = muscleGroup!!.name
)