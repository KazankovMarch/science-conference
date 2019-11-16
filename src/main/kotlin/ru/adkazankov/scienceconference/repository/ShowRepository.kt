package ru.adkazankov.scienceconference.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.adkazankov.scienceconference.domain.Show

@Repository
class ShowRepository(
        val jpaRepository: JpaRepository<Show, Long>
): MyRepository<Show> {
    override fun save(entity: Show) = jpaRepository.save(entity)
    override fun delete(entity: Show) = jpaRepository.delete(entity)
    override fun findAll(): List<Show> = jpaRepository.findAll()
}