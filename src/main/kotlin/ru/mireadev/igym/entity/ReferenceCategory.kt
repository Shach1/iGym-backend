package ru.mireadev.igym.entity

import jakarta.persistence.*

@Entity
@Table(name = "reference_categories")
data class ReferenceCategory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    val id: Int = 0,

    @Column(nullable = false, unique = true)
    val name: String = "Name"
){
    constructor() : this(0)
}