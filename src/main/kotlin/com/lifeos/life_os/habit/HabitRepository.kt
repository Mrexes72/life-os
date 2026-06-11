package com.lifeos.life_os.habit

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HabitRepository : JpaRepository<Habit, Long> {

}
