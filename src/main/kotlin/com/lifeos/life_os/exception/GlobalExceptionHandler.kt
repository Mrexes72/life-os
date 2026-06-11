package com.lifeos.life_os.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<ValidationErrorResponse> {
        val fieldErrors = mutableMapOf<String, String?>()
        ex.bindingResult.fieldErrors.forEach { error ->
            fieldErrors[error.field] = error.defaultMessage
        }

        val errorResponse = ValidationErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.BAD_REQUEST.value(),
            error = "Validation failed",
            message = "One or more fields were invalid",
            errors = fieldErrors
        )

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }
}

data class ValidationErrorResponse(
    val timestamp : LocalDateTime,
    val status : Int,
    val error : String,
    val message : String,
    val errors: Map<String, String?>
)