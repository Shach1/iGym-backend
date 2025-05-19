package ru.mireadev.igym.dto

import java.time.LocalDate

data class RegisterRequest(
    val username: String,
    val email: String,
    val passwordHash: String,
    val fullName: String,
    val bio: String? = null,
    val dateOfBirth: LocalDate? = null
)