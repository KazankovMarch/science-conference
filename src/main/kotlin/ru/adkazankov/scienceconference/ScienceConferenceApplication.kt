package ru.adkazankov.scienceconference

import javafx.scene.Scene
import javafx.stage.Stage
import org.mybatis.spring.annotation.MapperScan
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.SpringBootApplication
import ru.adkazankov.scienceconference.control.ControllerConfiguration

@SpringBootApplication
@MapperScan("ru.adkazankov.scienceconference.repository")
class ScienceConferenceApplication : AbstractFxApplicationSupport() {

    @Autowired
    @Qualifier("mainFrame")
    private lateinit var view: ControllerConfiguration.View

    override fun start(stage: Stage) {
        stage.scene = Scene(view.parent)
        stage.show()
    }
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            lauchApp(ScienceConferenceApplication::class.java, args)
        }
    }
}


