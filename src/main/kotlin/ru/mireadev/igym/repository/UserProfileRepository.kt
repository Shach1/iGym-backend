package ru.mireadev.igym.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.mireadev.igym.entity.UserProfile

interface UserProfileRepository : JpaRepository<UserProfile, Long> {
    fun deleteByUserId(userId: Long)
}