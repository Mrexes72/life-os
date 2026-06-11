package com.lifeos.life_os.habit

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.kotlin.whenever
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.web.server.ResponseStatusException
import kotlin.collections.mutableListOf
import kotlin.test.*
import  java.util.Optional

@ExtendWith(MockitoExtension::class)
class HabitServiceTest {

    @Mock
    lateinit var habitRepository: HabitRepository

    @InjectMocks
    lateinit var habitService: HabitService

    @Test
    fun `getAllHabits returns all habits`() {
        // Arrange – hva skal habitRepository returnere når findAll() kalles?
        val habits = mutableListOf(
                Habit(name = "Løping", description = "30 minutes", streakDays = 5),
        Habit(name = "Lesing", description = "20 minutes", streakDays = 3),
        )

        whenever(habitRepository.findAll()).thenReturn(habits)

        // Act – kall habitService.getAllHabits()
        val result = habitService.getAllHabits()
        // Assert – sjekk at resultatet er som forventet
        assertEquals(2, result.size)
        assertTrue(result.any  { it.name == "Lesing"})
    }

    @Test
    fun `getHabitById is Valid`() {
        val habit = Habit(id = 1L, name = "Lese", description = "30 minutes", streakDays = 5)

        whenever(habitRepository.findById(1L)).thenReturn(Optional.of(habit))

        val result = habitService.getHabitById(1L)

        assertNotNull(result)
        assertEquals("Lese", result.name)
        assertEquals(5, result.streakDays)
    }

    @Test
    fun `getHabitById is Not Valid`() {
        whenever(habitRepository.findById(99L)).thenReturn(Optional.empty())

        assertThrows(ResponseStatusException::class.java) {
            habitService.getHabitById(99L)
        }
    }
}
