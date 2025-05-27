package ru.mireadev.igym.entity.workout

import jakarta.persistence.*


@Entity
@Table(name = "workouts")
data class Workout(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workout_id")
    val id: Long = 0,

    @Column(nullable = false)
    val title: String = "",

    @Column(nullable = false, columnDefinition = "TEXT")
    val description: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "muscle_group_id", nullable = false)
    val muscleGroup: MuscleGroup? = null,

    @ManyToMany
    @JoinTable(
        name = "workout_exercises",
        joinColumns = [JoinColumn(name = "workout_id")],
        inverseJoinColumns = [JoinColumn(name = "exercise_id")]
    )
    @OrderColumn(name = "exercise_order") // Добавляем аннотацию для порядка
    val exercises: List<Exercise> = emptyList()
){
    constructor() : this(0)
}