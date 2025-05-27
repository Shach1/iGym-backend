package ru.mireadev.igym.repository.workout

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.mireadev.igym.entity.workout.Workout
import java.util.*

interface WorkoutRepository : JpaRepository<Workout, Long> {
    fun findByMuscleGroupIdOrderByTitleAsc(muscleGroupId: Long): List<Workout>
    fun findByTitleContainingIgnoreCase(title: String): List<Workout>

    @Query("""
        SELECT DISTINCT w FROM Workout w
        LEFT JOIN FETCH w.exercises e
        LEFT JOIN FETCH e.muscleGroup
        WHERE w.id = :id
    """)
    fun findByIdWithExercises(@Param("id") id: Long): Optional<Workout>
}