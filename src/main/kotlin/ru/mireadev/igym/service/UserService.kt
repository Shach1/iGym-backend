package ru.mireadev.igym.service

import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.mireadev.igym.dto.ErrorResponse
import ru.mireadev.igym.dto.RegisterRequest
import ru.mireadev.igym.dto.RegisterResponse
import ru.mireadev.igym.entity.User
import ru.mireadev.igym.repository.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    sealed class RegistrationResult {
        data class Success(val response: RegisterResponse) : RegistrationResult()
        data class Conflict(val error: ErrorResponse) : RegistrationResult()
        data class ValidationError(val error: ErrorResponse) : RegistrationResult()
    }

    @Transactional
    fun register(request: RegisterRequest): RegisterResponse {
        // Проверка уникальности username и email
        if (userRepository.existsByUsername(request.username)) {
            throw IllegalArgumentException("Username already exists")
        }
        if (userRepository.existsByEmail(request.email)) {
            throw IllegalArgumentException("Email already exists")
        }

        val passwordHash = passwordEncoder.encode(request.password)
        // Создание и сохранение пользователя
        val user = User(
            username = request.username,
            email = request.email,
            passwordHash = passwordHash,
            fullName = request.fullName,
            bio = request.bio,
            dateOfBirth = request.dateOfBirth
        )

        val savedUser = userRepository.save(user)

        return RegisterResponse(
            userID = savedUser.userId!!,
            username = savedUser.username,
            email = savedUser.email,
            fullName = savedUser.fullName,
            bio = savedUser.bio,
            dateOfBirth = savedUser.dateOfBirth
        )
    }
}