package ru.mireadev.igym.repository.workout

import org.springframework.data.jpa.repository.JpaRepository
import ru.mireadev.igym.entity.workout.Exercise

interface ExerciseRepository : JpaRepository<Exercise, Long> {
    fun findByMuscleGroupIdOrderByNameAsc(muscleGroupId: Long): List<Exercise>
}