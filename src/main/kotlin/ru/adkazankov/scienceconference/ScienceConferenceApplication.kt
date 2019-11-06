package ru.adkazankov.scienceconference

import javafx.stage.Stage
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ScienceConferenceApplication : AbstractFxApplicationSupport() {
    override fun start(stage: Stage?) {
        println(stage)
    }
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            lauchApp(ScienceConferenceApplication::class.java, args)
        }
    }
}


