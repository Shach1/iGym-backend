package ru.mireadev.igym.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<ErrorResponse> {
        return when (ex) {
            is BadCredentialsException -> ResponseEntity.status(401).body(ErrorResponse("Invalid credentials"))
            is AccessDeniedException -> ResponseEntity.status(403).body(ErrorResponse("Access denied"))
            else -> ResponseEntity.status(500).body(ErrorResponse("Internal server error"))
        }
    }
}

data class ErrorResponse(val message: String)