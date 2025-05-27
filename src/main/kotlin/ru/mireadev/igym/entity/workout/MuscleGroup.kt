package ru.mireadev.igym.entity.workout

import jakarta.persistence.*

@Entity
@Table(name = "muscle_groups")
data class MuscleGroup(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "muscle_group_id")
    val id: Long = 0,

    @Column(name = "name", nullable = false, unique = true)
    val name: String = "Name",

    @Column(name = "description", columnDefinition = "TEXT")
    val description: String? = "Description"
){
    constructor() : this(0)
}
