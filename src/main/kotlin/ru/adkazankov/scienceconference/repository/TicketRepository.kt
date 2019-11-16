package ru.adkazankov.scienceconference.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.adkazankov.scienceconference.domain.Ticket

@Repository
class TicketRepository(
        val jpaRepository: JpaRepository<Ticket, Long>
): MyRepository<Ticket> {
    override fun save(entity: Ticket) = jpaRepository.save(entity)
    override fun delete(entity: Ticket) = jpaRepository.delete(entity)
    override fun findAll(): List<Ticket> = jpaRepository.findAll()
}