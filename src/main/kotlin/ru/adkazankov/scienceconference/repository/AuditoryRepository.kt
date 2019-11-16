package ru.adkazankov.scienceconference.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.adkazankov.scienceconference.domain.Auditory

@Repository
class AuditoryRepository(
        val jpaRepository: JpaRepository<Auditory, Long>
): MyRepository<Auditory> {

    override fun save(entity: Auditory) = jpaRepository.save(entity)
    override fun delete(entity: Auditory) = jpaRepository.delete(entity)
    override fun findAll(): List<Auditory> = jpaRepository.findAll()
}
