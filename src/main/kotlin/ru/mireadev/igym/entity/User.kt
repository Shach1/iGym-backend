package ru.mireadev.igym.entity;

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User @JvmOverloads constructor(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id")
        val userId: Long? = null,

        @Column(name = "username", nullable = false, unique = true)
        val username: String = "",

        @Column(name = "email", nullable = false, unique = true)
        val email: String = "",

        @Column(name = "password_hash", nullable = false)
        val passwordHash: String = "",

        @Column(name = "full_name", nullable = false)
        val fullName: String = ""
)