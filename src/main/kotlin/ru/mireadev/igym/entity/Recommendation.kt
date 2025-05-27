package ru.mireadev.igym.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import ru.mireadev.igym.dto.RecommendationResponse
import java.time.LocalDate


@Entity
@Table(name = "recommendations")
data class Recommendation (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommendation_id")
    val id: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    val category: ReferenceCategory? = null,

    @Column(nullable = false)
    val title: String = "Tittle",

    @Column(nullable = false, columnDefinition = "TEXT")
    val content: String = "Content",

    @Column(name = "created_at", updatable = false)
    val createdAt: LocalDate = LocalDate.now()
){
    constructor() : this(0)
}

fun Recommendation.toResponse(): RecommendationResponse {
    return RecommendationResponse(
        id = this.id,
        title = this.title,
        content = this.content,
        createdAt = this.createdAt
    )
}