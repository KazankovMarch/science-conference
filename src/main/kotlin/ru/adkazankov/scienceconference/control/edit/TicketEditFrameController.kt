package ru.adkazankov.scienceconference.control.edit

import javafx.collections.FXCollections
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import ru.adkazankov.scienceconference.domain.Person
import ru.adkazankov.scienceconference.domain.Presentation
import ru.adkazankov.scienceconference.domain.Ticket
import ru.adkazankov.scienceconference.repository.PersonRepository
import ru.adkazankov.scienceconference.repository.PresentationRepository

@Controller
class TicketEditFrameController : AbstractEditFrameController<Ticket>() {

    @Autowired
    private lateinit var personRepository: PersonRepository
    @Autowired
    private lateinit var presentationRepository: PresentationRepository


    private lateinit var ticket: Ticket
    private lateinit var idField: TextField
    private lateinit var personBox: ComboBox<Person>
    private lateinit var presentationBox: ComboBox<Presentation>


    override fun fieldsToEntity() = Ticket().apply {
        person = personBox.value
        presentation = presentationBox.value
    }

    override fun createInterfaceFields(gridPane: GridPane, nullableEntity: Ticket?) {
        ticket = nullableEntity ?: Ticket()
        idField = TextField("${ticket.id}").apply { isEditable = false }
        personBox = ComboBox<Person>().apply {
            val items = FXCollections.observableArrayList(personRepository.findAll())
            setItems(items)
            selectionModel.select(items.firstOrNull{it.id == ticket.person?.id})
        }
        presentationBox = ComboBox<Presentation>().apply {
            val items = FXCollections.observableArrayList(presentationRepository.findAll())
            setItems(items)
            selectionModel.select(items.firstOrNull{it.id == ticket.presentation?.id})
        }
        gridPane.apply {
            addRow(1, Label("id"), idField)
            addRow(2, Label("person"), personBox)
            addRow(3, Label("presentation"), presentationBox)
        }
    }
}