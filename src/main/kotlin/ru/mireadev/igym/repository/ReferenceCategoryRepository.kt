package ru.mireadev.igym.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.mireadev.igym.entity.ReferenceCategory

interface ReferenceCategoryRepository : JpaRepository<ReferenceCategory, Int> {}