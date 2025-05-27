package ru.mireadev.igym.entity.workout

import jakarta.persistence.*
import java.io.Serializable


@Entity
@Table(name = "workout_exercises")
@IdClass(WorkoutExerciseId::class)
data class WorkoutExercise(
    @Id
    @ManyToOne
    @JoinColumn(name = "workout_id")
    val workout: Workout? = null,

    @Id
    @ManyToOne
    @JoinColumn(name = "exercise_id")
    val exercise: Exercise? = null,

    @Column(name = "exercise_order", nullable = false)
    val order: Int = 0
)

data class WorkoutExerciseId(
    val workout: Long = 0,
    val exercise: Long = 0
): Serializable