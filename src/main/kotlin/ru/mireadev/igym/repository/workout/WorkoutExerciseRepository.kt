package ru.mireadev.igym.repository.workout

import org.springframework.data.jpa.repository.JpaRepository
import ru.mireadev.igym.entity.workout.WorkoutExercise
import ru.mireadev.igym.entity.workout.WorkoutExerciseId

interface WorkoutExerciseRepository : JpaRepository<WorkoutExercise, WorkoutExerciseId> {
}