package com.lifeos.life_os.habit

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.*

@Entity
data class Habit (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @field:NotBlank(message = "Name is required")
    val name: String,
    @field:Size(max = 100, message = "Message cannot exceed 100 characters.")
    val description: String,
    @field:PositiveOrZero(message = "Streak days  cant be a negative number")
    val streakDays: Int = 0,
)
