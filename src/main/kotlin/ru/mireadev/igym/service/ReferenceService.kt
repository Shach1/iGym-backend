package ru.mireadev.igym.service

import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import ru.mireadev.igym.dto.RecommendationResponse
import ru.mireadev.igym.entity.Recommendation
import ru.mireadev.igym.entity.ReferenceCategory
import ru.mireadev.igym.entity.toResponse
import ru.mireadev.igym.repository.RecommendationRepository
import ru.mireadev.igym.repository.ReferenceCategoryRepository

@Service
class ReferenceService(
    private val categoryRepository: ReferenceCategoryRepository,
    private val recommendationRepository: RecommendationRepository
) {
    fun getAllCategories(): List<ReferenceCategory> {
        return categoryRepository.findAll()
    }

    fun getRecommendationsByCategoryId(categoryId: Int): List<RecommendationResponse> {
        if (!categoryRepository.existsById(categoryId)) {
            throw ChangeSetPersister.NotFoundException()
        }

        return recommendationRepository.findByCategoryId(categoryId)
            .map { it.toResponse() }
    }

}