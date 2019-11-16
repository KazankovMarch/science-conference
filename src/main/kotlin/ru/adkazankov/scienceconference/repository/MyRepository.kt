package ru.adkazankov.scienceconference.repository

import ru.adkazankov.scienceconference.domain.Auditory

interface MyRepository<E> {

    fun save(entity: E): Auditory

    fun delete(entity: E)

    fun findAll(): List<E>
}