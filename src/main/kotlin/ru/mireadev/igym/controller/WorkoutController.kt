package ru.mireadev.igym.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.mireadev.igym.dto.workout.ExerciseResponse
import ru.mireadev.igym.dto.workout.MuscleGroupResponse
import ru.mireadev.igym.dto.workout.WorkoutResponse
import ru.mireadev.igym.dto.workout.WorkoutShortResponse
import ru.mireadev.igym.service.WorkoutService


@RestController
@RequestMapping("/api/workouts")
@Tag(name = "Workout API", description = "API для получения данных о тренировках")
class WorkoutController(
    private val workoutService: WorkoutService
) {
    @GetMapping("/muscle-groups")
    @Operation(summary = "Получить все группы мышц")
    fun getAllMuscleGroups(): ResponseEntity<List<MuscleGroupResponse>> {
        return ResponseEntity.ok(workoutService.getAllMuscleGroups())
    }

    @GetMapping("/exercises")
    @Operation(summary = "Получить упражнения по группе мышц")
    fun getExercisesByMuscleGroup(
        @Parameter(description = "ID группы мышц", example = "1")
        @RequestParam muscleGroupId: Long
    ): ResponseEntity<List<ExerciseResponse>> {
        return ResponseEntity.ok(workoutService.getExercisesByMuscleGroup(muscleGroupId))
    }

    @GetMapping
    @Operation(summary = "Получить все тренировки (краткая информация)")
    fun getAllWorkouts(): ResponseEntity<List<WorkoutShortResponse>> {
        return ResponseEntity.ok(workoutService.getAllWorkouts())
    }

    @GetMapping("/{workoutId}")
    @Operation(summary = "Получить детали тренировки")
    fun getWorkoutDetails(
        @Parameter(description = "ID тренировки", example = "1")
        @PathVariable workoutId: Long
    ): ResponseEntity<WorkoutResponse> {
        return ResponseEntity.ok(workoutService.getWorkoutDetails(workoutId))
    }
}