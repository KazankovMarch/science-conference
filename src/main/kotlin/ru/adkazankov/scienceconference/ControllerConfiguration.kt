package ru.adkazankov.scienceconference

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.adkazankov.scienceconference.control.MainFrameController
import java.io.IOException

@Configuration
class ControllerConfiguration {

    @Bean("mainFrame")
    @Throws(IOException::class)
    fun getMainFrame(): View {
        return loadView("view.fxml/MainFrame.fxml")
    }

    /**
     * Именно благодаря этому методу мы добавили контроллер в контекст спринга,
     * и заставили его произвести все необходимые инъекции.
     */
    @Bean
    @Throws(IOException::class)
    fun getMainController(): MainFrameController {
        return getMainFrame().controller as MainFrameController
    }

    /**
     * Самый обыкновенный способ использовать FXML загрузчик.
     * Как раз-таки на этом этапе будет создан объект-контроллер,
     * произведены все FXML инъекции и вызван метод инициализации контроллера.
     */
    @Throws(IOException::class)
    protected fun loadView(url: String): View {
        javaClass.classLoader.getResourceAsStream(url).use {
            val loader = FXMLLoader()
            loader.load<Any>(it)
            return View(loader.getRoot<Parent>(), loader.getController<Any>())
        }
    }

    /**
     * Класс - оболочка: контроллер мы обязаны указать в качестве бина,
     * а view - представление, нам предстоит использовать в точке входа.
     */
    data class View(val parent: Parent, val controller: Any)
}

