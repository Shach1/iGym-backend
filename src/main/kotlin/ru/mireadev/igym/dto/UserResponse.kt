package ru.mireadev.igym.dto

import java.time.LocalDate

data class UserResponse(
    val userID: Long,
    val username: String,
    val email: String,
    val fullName: String,
    val bio: String?,
    val dateOfBirth: LocalDate?
)