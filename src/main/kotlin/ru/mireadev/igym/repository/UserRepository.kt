package ru.mireadev.igym.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.mireadev.igym.entity.User

interface UserRepository : JpaRepository<User, Long> {
    fun existsByUsername(username: String): Boolean
    fun existsByEmail(email: String): Boolean
}