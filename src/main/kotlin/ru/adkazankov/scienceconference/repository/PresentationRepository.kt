package ru.adkazankov.scienceconference.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.adkazankov.scienceconference.domain.Presentation

@Repository
class PresentationRepository(
        val jpaRepository: JpaRepository<Presentation, Long>
): MyRepository<Presentation> {
    override fun save(entity: Presentation) = jpaRepository.save(entity)
    override fun delete(entity: Presentation) = jpaRepository.delete(entity)
    override fun findAll(): List<Presentation> = jpaRepository.findAll()
}