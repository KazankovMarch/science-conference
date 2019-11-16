package ru.adkazankov.scienceconference.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.adkazankov.scienceconference.domain.Person

interface PersonRepository : JpaRepository<Person, Long>, MyRepository<Person>