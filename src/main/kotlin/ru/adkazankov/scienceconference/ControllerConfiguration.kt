package ru.adkazankov.scienceconference

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.JpaRepository
import ru.adkazankov.scienceconference.control.AbstractTabController
import ru.adkazankov.scienceconference.control.MainFrameController
import ru.adkazankov.scienceconference.domain.Auditory
import ru.adkazankov.scienceconference.domain.Person
import java.io.IOException

private const val ABSTRACT_TAB_FRAME = "view.fxml/AbstractTab.fxml"

@Configuration
class ControllerConfiguration {

    @Bean("tabControllers")
    fun getTabControllers(
            @Autowired auditoryTabController: AbstractTabController<Auditory>,
            @Autowired personTabController: AbstractTabController<Person>
    )= listOf(auditoryTabController,personTabController)

    @Bean("auditoryTab")
    fun getAuditoryTab(): View = loadView(ABSTRACT_TAB_FRAME)
    @Bean
    fun getAuditoryTabController(
            @Autowired jpaRepository: JpaRepository<Auditory, Long>
    ): AbstractTabController<Auditory> {
        val controller = getAuditoryTab().controller as AbstractTabController<Auditory>
        return controller.apply {
            entityType = Auditory::class.java
            name = "Auditories"
            repository = jpaRepository
        }
    }


    @Bean("personTab")
    fun getPersonTab(): View = loadView(ABSTRACT_TAB_FRAME)
    @Bean
    fun getPersonTabController(
            @Autowired jpaRepository: JpaRepository<Person, Long>
    ): AbstractTabController<Person> {
        val controller = getPersonTab ().controller as AbstractTabController<Person>
        return controller.apply {
            entityType = Person::class.java
            name = "Persons"
            repository = jpaRepository
        }
    }

    @Bean("mainFrame")
    @Throws(IOException::class)
    fun getMainFrame(): View = loadView("view.fxml/MainFrame.fxml")

    /**
     * Именно благодаря этому методу мы добавили контроллер в контекст спринга,
     * и заставили его произвести все необходимые инъекции.
     */
    @Bean
    @Throws(IOException::class)
    fun getMainController()
            = getMainFrame().controller as MainFrameController

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

