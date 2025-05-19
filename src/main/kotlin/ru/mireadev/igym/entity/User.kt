package ru.mireadev.igym.entity;

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "users")
data class User @JvmOverloads constructor(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id")
        val userId: Long? = null,

        @Column(name = "username", nullable = false, unique = true)
        val username: String = "", // default value

        @Column(name = "email", nullable = false, unique = true)
        val email: String = "", // default value

        @Column(name = "password_hash", nullable = false)
        val passwordHash: String = "", // default value

        @Column(name = "full_name", nullable = false)
        val fullName: String = "", // default value

        @Column(name = "bio", columnDefinition = "TEXT")
        val bio: String? = null,

        @Column(name = "date_of_birth")
        val dateOfBirth: LocalDate? = null
)