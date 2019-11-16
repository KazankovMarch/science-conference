package ru.adkazankov.scienceconference.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.adkazankov.scienceconference.domain.Auditory

interface AuditoryRepository: JpaRepository<Auditory, Long>, MyRepository<Auditory>
