package com.lifeos.life_os.habit

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException
import kotlin.jvm.optionals.getOrNull

@Service
class HabitService(
    private val habitRepository: HabitRepository
) {
    fun getAllHabits(): List<Habit> {
        return habitRepository.findAll()
    }

    fun getHabitById(id: Long): Habit {
        return habitRepository.findById(id).getOrNull()
        ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun createHabit(habit: Habit): Habit {
        return habitRepository.save(habit)
    }

    fun updateHabit(habit: Habit): Habit {
        return habitRepository.save(habit)
    }

    fun deleteHabitById(id: Long) {
        habitRepository.deleteById(id)
    }
}