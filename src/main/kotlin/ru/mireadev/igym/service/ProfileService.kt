package ru.mireadev.igym.service

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.mireadev.igym.dto.ErrorResponse
import ru.mireadev.igym.dto.ProfileRequest
import ru.mireadev.igym.dto.ProfileResponse
import ru.mireadev.igym.entity.UserProfile
import ru.mireadev.igym.repository.UserProfileRepository
import ru.mireadev.igym.repository.UserRepository
import java.time.LocalDate
import java.time.Period

@Service
class ProfileService(
    private val profileRepository: UserProfileRepository,
    private val userRepository: UserRepository
) {
    @Transactional
    fun createOrUpdateProfile(request: ProfileRequest): ResponseEntity<Any> {
        return try {
            var user = userRepository.findById(request.userId)
                .orElseThrow { NoSuchElementException("User not found with id: ${request.userId}") }

            val profile = profileRepository.findById(request.userId).orElseGet {
                UserProfile().apply {
                    userId = request.userId
                    user = user
                }
            }.apply {
                sex = request.sex
                birthDate = request.birthDate
                height = request.height
                weight = request.weight
            }

            val savedProfile = profileRepository.save(profile)
            ResponseEntity.ok(toResponse(savedProfile))
        } catch (e: NoSuchElementException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse(success = false, message = "User not found", errorCode = "404"))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest()
                .body(ErrorResponse(success = false, message = "Invalid request data", errorCode = "400"))
        }
    }

    fun getProfile(userId: Long): ResponseEntity<Any> {
        return try {
            val profile = profileRepository.findById(userId)
                .orElseThrow { NoSuchElementException("Profile not found for user id: $userId") }
            ResponseEntity.ok(toResponse(profile))
        } catch (e: NoSuchElementException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse(success = false, message = "Profile not found", errorCode = "404"))
        }
    }

    private fun toResponse(profile: UserProfile): ProfileResponse {
        return ProfileResponse(
            userId = profile.userId,
            sex = profile.sex,
            birthDate = profile.birthDate,
            height = profile.height,
            weight = profile.weight,
            age = Period.between(profile.birthDate, LocalDate.now()).years
        )
    }
}