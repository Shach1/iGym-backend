package ru.mireadev.igym.repository.workout

import org.springframework.data.jpa.repository.JpaRepository
import ru.mireadev.igym.entity.workout.MuscleGroup


interface MuscleGroupRepository : JpaRepository<MuscleGroup, Long> {
    fun findAllByOrderByNameAsc(): List<MuscleGroup>
}