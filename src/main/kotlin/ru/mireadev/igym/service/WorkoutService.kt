package ru.mireadev.igym.service

import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import ru.mireadev.igym.dto.workout.*
import ru.mireadev.igym.repository.workout.*

@Service
class WorkoutService(
    private val muscleGroupRepository: MuscleGroupRepository,
    private val exerciseRepository: ExerciseRepository,
    private val workoutRepository: WorkoutRepository
) {
    fun getAllMuscleGroups(): List<MuscleGroupResponse> {
        return muscleGroupRepository.findAllByOrderByNameAsc()
            .map { it.toResponse() }
    }

    fun getExercisesByMuscleGroup(muscleGroupId: Long): List<ExerciseResponse> {
        return exerciseRepository.findByMuscleGroupIdOrderByNameAsc(muscleGroupId)
            .map { it.toResponse() }
    }

    fun getAllWorkouts(): List<WorkoutShortResponse> {
        return workoutRepository.findAll()
            .map { it.toShortResponse() }
    }

    fun getWorkoutDetails(workoutId: Long): WorkoutResponse {
        val workout = workoutRepository.findByIdWithExercises(workoutId)
            .orElseThrow { ChangeSetPersister.NotFoundException() }

        return WorkoutResponse.fromEntity(workout)
    }
}