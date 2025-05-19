package ru.mireadev.igym.dto

data class AuthResponse(
    val token: String,
    val email: String,
    val username: String
)
