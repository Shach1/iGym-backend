package ru.mireadev.igym.entity.workout

import jakarta.persistence.*

@Entity
@Table(name = "exercises")
data class Exercise(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id")
    val id: Long = 0,

    @Column(name = "name", nullable = false, unique = true)
    val name: String = "Name",

    @Column(name = "description",nullable = false, columnDefinition = "TEXT")
    val description: String = "Description",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "muscle_group_id", nullable = false)
    val muscleGroup: MuscleGroup ? = null
){
    constructor() : this(0)
}