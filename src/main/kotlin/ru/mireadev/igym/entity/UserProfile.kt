package ru.mireadev.igym.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import java.time.LocalDate

@Entity
@Table(name = "user_profiles")
data class UserProfile(
    @Id
    @Column(name = "user_id")
    var userId: Long = 0,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id",
        insertable = false, updatable = false)
    var user: User? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 6)
    var sex: Sex = Sex.MALE,

    @Column(nullable = false)
    var birthDate: LocalDate = LocalDate.now(),

    @Column(nullable = false)
    @Min(100) @Max(250)
    var height: Int = 170,

    @Column(nullable = false)
    @Min(30) @Max(300)
    var weight: Double = 70.0
) {
    constructor() : this(0)
}
enum class Sex {
    MALE, FEMALE
}