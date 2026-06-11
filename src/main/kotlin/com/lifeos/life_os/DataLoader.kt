package com.lifeos.life_os

import com.lifeos.life_os.habit.Habit
import com.lifeos.life_os.habit.HabitRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DataLoaderConfig(private val habitRepository: HabitRepository) {

    @Bean
    fun dataLoaderRunner() = ApplicationRunner {
        val habits = mutableListOf(
            Habit(name = "Løping", description = "30 minutes", streakDays = 5),
            Habit(name = "Lesing", description = "20 minutes", streakDays = 3),
        )
        habitRepository.saveAll(habits)
    }
}