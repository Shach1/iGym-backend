package ru.mireadev.igym.service

import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.mireadev.igym.dto.AuthResponse
import ru.mireadev.igym.dto.LoginRequest
import ru.mireadev.igym.repository.UserRepository

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val userDetailsService: UserDetailsServiceImpl
) {
    fun authenticate(request: LoginRequest): AuthResponse {
        val user = userRepository.findByEmail(request.email)
            ?: throw BadCredentialsException("Invalid email or password")

        if (!passwordEncoder.matches(request.password, user.passwordHash)) {
            throw BadCredentialsException("Invalid email or password")
        }

        val userDetails = userDetailsService.loadUserByUsername(user.email)
        val token = jwtService.generateToken(userDetails)

        return AuthResponse(
            token = token,
            email = user.email,
            username = user.username
        )
    }
}