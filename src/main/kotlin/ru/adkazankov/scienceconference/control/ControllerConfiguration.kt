package ru.adkazankov.scienceconference.control

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.JpaRepository
import ru.adkazankov.scienceconference.control.edit.AbstractEditFrameController
import ru.adkazankov.scienceconference.domain.*
import java.io.IOException

private const val ABSTRACT_TAB_FRAME = "view.fxml/AbstractTab.fxml"

@Configuration
class ControllerConfiguration {

    @Bean("tabControllers")
    fun getTabControllers(
            @Autowired auditoryTabController: AbstractEntityTabController<Auditory>,
            @Autowired personTabController: AbstractEntityTabController<Person>
    )= listOf(auditoryTabController,personTabController)


    @Bean("ticketTab")
    fun getTicketTab(): View = loadView(ABSTRACT_TAB_FRAME)
    @Bean
    fun getTicketTabController(
            @Autowired jpaRepository: JpaRepository<Ticket, Long>,
            @Autowired abstractEditFrameController: AbstractEditFrameController<Ticket>
    ): AbstractEntityTabController<Ticket> {
        val controller = getTicketTab().controller as AbstractEntityTabController<Ticket>
        return controller.apply {
            this.entityType = Ticket::class.java
            this.name = "Tickets"
            this.repository = jpaRepository
            this.abstractEditFrameController = abstractEditFrameController
        }
    }


    @Bean("presentationTab")
    fun getPresentationTab(): View = loadView(ABSTRACT_TAB_FRAME)
    @Bean
    fun getPresentationTabController(
            @Autowired jpaRepository: JpaRepository<Presentation, Long>,
            @Autowired abstractEditFrameController: AbstractEditFrameController<Presentation>
    ): AbstractEntityTabController<Presentation> {
        val controller = getPresentationTab().controller as AbstractEntityTabController<Presentation>
        return controller.apply {
            this.entityType = Presentation::class.java
            this.name = "Presentations"
            this.repository = jpaRepository
            this.abstractEditFrameController = abstractEditFrameController
        }
    }

    @Bean("speakerTab")
    fun getSpeakerTab(): View = loadView(ABSTRACT_TAB_FRAME)
    @Bean
    fun getSpeakerTabController(
            @Autowired jpaRepository: JpaRepository<Speaker, Long>,
            @Autowired abstractEditFrameController: AbstractEditFrameController<Speaker>
    ): AbstractEntityTabController<Speaker> {
        val controller = getSpeakerTab().controller as AbstractEntityTabController<Speaker>
        return controller.apply {
            this.entityType = Speaker::class.java
            this.name = "Speakers"
            this.repository = jpaRepository
            this.abstractEditFrameController = abstractEditFrameController
        }
    }


    @Bean("companyTab")
    fun getCompanyTab(): View = loadView(ABSTRACT_TAB_FRAME)
    @Bean
    fun getCompanyTabController(
            @Autowired jpaRepository: JpaRepository<Company, Long>,
            @Autowired abstractEditFrameController: AbstractEditFrameController<Company>
    ): AbstractEntityTabController<Company> {
        val controller = getCompanyTab().controller as AbstractEntityTabController<Company>
        return controller.apply {
            this.entityType = Company::class.java
            this.name = "Companies"
            this.repository = jpaRepository
            this.abstractEditFrameController = abstractEditFrameController
        }
    }


    @Bean("auditoryTab")
    fun getAuditoryTab(): View = loadView(ABSTRACT_TAB_FRAME)
    @Bean
    fun getAuditoryTabController(
            @Autowired jpaRepository: JpaRepository<Auditory, Long>,
            @Autowired abstractEditFrameController: AbstractEditFrameController<Auditory>
    ): AbstractEntityTabController<Auditory> {
        val controller = getAuditoryTab().controller as AbstractEntityTabController<Auditory>
        return controller.apply {
            this.entityType = Auditory::class.java
            this.name = "Auditories"
            this.repository = jpaRepository
            this.abstractEditFrameController = abstractEditFrameController
        }
    }


    @Bean("personTab")
    fun getPersonTab(): View = loadView(ABSTRACT_TAB_FRAME)
    @Bean
    fun getPersonTabController(
            @Autowired jpaRepository: JpaRepository<Person, Long>,
            @Autowired abstractEditFrameController: AbstractEditFrameController<Person>
    ): AbstractEntityTabController<Person> {
        val controller = getPersonTab ().controller as AbstractEntityTabController<Person>
        return controller.apply {
            this.entityType = Person::class.java
            this.name = "Persons"
            this.repository = jpaRepository
            this.abstractEditFrameController = abstractEditFrameController
        }
    }

    @Bean("selectTab")
    fun getSelectTab(): View = loadView("view.fxml/SelectTab.fxml")
    @Bean
    fun getSelectTabController(): SelectTabController {
        val controller = getSelectTab().controller as SelectTabController
        return controller
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

