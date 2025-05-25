package ru.mireadev.igym.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.mireadev.igym.entity.Recommendation

interface RecommendationRepository : JpaRepository<Recommendation, Int> {
    fun findByCategoryId(categoryId: Int): List<Recommendation>
    fun existsByCategoryId(categoryId: Int): Boolean

}
