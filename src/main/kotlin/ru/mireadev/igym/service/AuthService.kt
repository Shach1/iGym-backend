package ru.mireadev.igym.service

import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.mireadev.igym.dto.*
import ru.mireadev.igym.entity.User
import ru.mireadev.igym.repository.UserRepository

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val userDetailsService: UserDetailsServiceImpl
) {

    sealed class RegistrationResult {
        data class Success(val response: RegisterResponse) : RegistrationResult()
        data class Conflict(val error: ErrorResponse) : RegistrationResult()
        data class ValidationError(val error: ErrorResponse) : RegistrationResult()
    }

    fun registerUser(request: RegisterRequest): RegistrationResult {
        // Проверка уникальности
        if (userRepository.existsByUsername(request.username)) {
            return RegistrationResult.Conflict(
                ErrorResponse(
                    success = false,
                    message = "Имя пользователя уже занято",
                    errorCode = "USERNAME_EXISTS"
                )
            )
        }

        if (userRepository.existsByEmail(request.email)) {
            return RegistrationResult.Conflict(
                ErrorResponse(
                    success = false,
                    message = "Email уже зарегистрирован",
                    errorCode = "EMAIL_EXISTS"
                )
            )
        }

        // Создание пользователя
        val user = User(
            username = request.username,
            email = request.email,
            passwordHash = passwordEncoder.encode(request.password),
            fullName = request.fullName,
        )

        val savedUser = userRepository.save(user)

        return RegistrationResult.Success(
            RegisterResponse(
                userID = savedUser.userId!!,
                username = savedUser.username,
                email = savedUser.email,
                fullName = savedUser.fullName
            )
        )
    }

    sealed class AuthResult {
        data class Success(val response: AuthResponse) : AuthResult()
        data class InvalidCredentials(val error: ErrorResponse) : AuthResult()
    }

    fun authenticate(request: LoginRequest): AuthResult {
        val user = userRepository.findByEmail(request.email)
            ?: return AuthResult.InvalidCredentials(
                ErrorResponse(
                    success = false,
                    message = "Пользователь с такой почтой не найден",
                    errorCode = "AUTH_FAILED"
                )
            )

        if (!passwordEncoder.matches(request.password, user.passwordHash)) {
            return AuthResult.InvalidCredentials(
                ErrorResponse(
                    success = false,
                    message = "Неверный пароль",
                    errorCode = "AUTH_FAILED"
                )
            )

        }
        val userDetails = userDetailsService.loadUserByUsername(user.email)
        return AuthResult.Success(
            AuthResponse(
                token = jwtService.generateToken(userDetails),
                email = user.email,
                username = user.username
            )
        )
    }


}