package ru.adkazankov.scienceconference.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.adkazankov.scienceconference.domain.Presentation

@Repository
interface PresentationRepository: MyRepository<Presentation>, JpaRepository<Presentation, Long>