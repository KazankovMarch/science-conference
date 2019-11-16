package ru.adkazankov.scienceconference.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import ru.adkazankov.scienceconference.domain.Show

interface JpaShowRepo: JpaRepository<Show, Long>