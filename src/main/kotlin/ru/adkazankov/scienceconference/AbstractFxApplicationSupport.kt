package ru.adkazankov.scienceconference

import javafx.application.Application
import org.springframework.boot.SpringApplication
import org.springframework.context.ConfigurableApplicationContext

abstract class AbstractFxApplicationSupport : Application() {

    protected lateinit var context: ConfigurableApplicationContext

    override fun init() {
        context = SpringApplication.run(this.javaClass, *savedArgs)
        context.autowireCapableBeanFactory.autowireBean(this)
    }

    override fun stop() {
        super.stop()
        context.close()
    }

    protected companion object {
        private lateinit var savedArgs: Array<String>
        fun lauchApp(clazz: Class<out AbstractFxApplicationSupport>, args: Array<String>){
            savedArgs = args
            launch(clazz, *args)
        }
    }
}