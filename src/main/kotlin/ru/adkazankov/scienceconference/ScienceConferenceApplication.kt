package ru.adkazankov.scienceconference

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ScienceConferenceApplication

fun main(args: Array<String>) {
    runApplication<ScienceConferenceApplication>(*args)
}
