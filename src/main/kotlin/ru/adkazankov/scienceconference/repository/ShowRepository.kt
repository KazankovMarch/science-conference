package ru.adkazankov.scienceconference.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.adkazankov.scienceconference.domain.Show

interface ShowRepository: MyRepository<Show>, JpaRepository<Show, Long>