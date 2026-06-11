package com.lifeos.life_os.habit

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/habits")
class HabitController(private val habitService: HabitService) {

    @GetMapping
    fun getAllHabits(): List<Habit> {
        return habitService.getAllHabits()
    }

    @GetMapping("/{id}")
    fun getHabitById(@PathVariable id: Long): Habit {
        return habitService.getHabitById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createHabit(@Valid @RequestBody newHabit: Habit): Habit {
        val savedHabit =  habitService.createHabit(newHabit)
        return savedHabit
    }

    @PutMapping("/{id}")
    fun updateHabit(
        @PathVariable id: Long,
        @Valid @RequestBody updatedData: Habit
    ): Habit {
        habitService.getHabitById(id)
        return habitService.updateHabit(updatedData.copy(id = id))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteHabitById(@PathVariable id: Long) {
        habitService.deleteHabitById(id)
    }
}
