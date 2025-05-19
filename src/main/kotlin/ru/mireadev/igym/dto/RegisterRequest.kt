package ru.mireadev.igym.dto

import java.time.LocalDate

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val fullName: String,
    val bio: String? = null,
    val dateOfBirth: LocalDate? = null
)