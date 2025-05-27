package ru.mireadev.igym.dto.workout

import ru.mireadev.igym.entity.workout.Workout

data class WorkoutResponse(
    val id: Long,
    val title: String,
    val description: String,
    val muscleGroup: String,
    val exercises: List<ExerciseResponse>
) {
    companion object {
        fun fromEntity(workout: Workout): WorkoutResponse {
            return WorkoutResponse(
                id = workout.id,
                title = workout.title,
                description = workout.description,
                muscleGroup = workout.muscleGroup!!.name,
                exercises = workout.exercises.mapNotNull { exercise ->
                    exercise?.let {
                        ExerciseResponse(
                            id = it.id,
                            name = it.name,
                            description = it.description
                        )
                    }
                }
            )
        }
    }
}
