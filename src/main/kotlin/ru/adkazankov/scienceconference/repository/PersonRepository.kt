package ru.adkazankov.scienceconference.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.adkazankov.scienceconference.domain.Person

@Repository
class PersonRepository(
        val jpaRepository: JpaRepository<Person, Long>
): MyRepository<Person> {
    override fun save(entity: Person) = jpaRepository.save(entity)
    override fun delete(entity: Person) = jpaRepository.delete(entity)
    override fun findAll(): List<Person> = jpaRepository.findAll()
}