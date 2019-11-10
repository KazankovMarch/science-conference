package ru.adkazankov.scienceconference.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.adkazankov.scienceconference.domain.Speaker

interface SpeakerRepository: JpaRepository<Speaker, Long>