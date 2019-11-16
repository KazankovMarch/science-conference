package ru.adkazankov.scienceconference.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import ru.adkazankov.scienceconference.domain.Auditory

interface JpaAuditoryRepo: JpaRepository<Auditory, Long>