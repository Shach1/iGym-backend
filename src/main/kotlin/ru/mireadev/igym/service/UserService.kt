package ru.mireadev.igym.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import ru.mireadev.igym.dto.RegisterRequest
import ru.mireadev.igym.dto.UserResponse
import ru.mireadev.igym.entity.User
import ru.mireadev.igym.repository.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository
) {

    @Transactional
    fun register(request: RegisterRequest): UserResponse {
        // Проверка уникальности username и email
        if (userRepository.existsByUsername(request.username)) {
            throw IllegalArgumentException("Username already exists")
        }
        if (userRepository.existsByEmail(request.email)) {
            throw IllegalArgumentException("Email already exists")
        }

        // Создание и сохранение пользователя
        val user = User(
            username = request.username,
            email = request.email,
            passwordHash = request.passwordHash,
            fullName = request.fullName,
            bio = request.bio,
            dateOfBirth = request.dateOfBirth
        )

        val savedUser = userRepository.save(user)

        return UserResponse(
            userID = savedUser.userId!!,
            username = savedUser.username,
            email = savedUser.email,
            fullName = savedUser.fullName,
            bio = savedUser.bio,
            dateOfBirth = savedUser.dateOfBirth
        )
    }
}