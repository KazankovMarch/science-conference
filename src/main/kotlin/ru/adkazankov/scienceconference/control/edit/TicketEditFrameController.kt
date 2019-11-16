package ru.adkazankov.scienceconference.control.edit

import javafx.collections.FXCollections
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import ru.adkazankov.scienceconference.domain.Person
import ru.adkazankov.scienceconference.domain.Show
import ru.adkazankov.scienceconference.domain.Ticket
import ru.adkazankov.scienceconference.repository.PersonRepository
import ru.adkazankov.scienceconference.repository.ShowRepository

@Controller
class TicketEditFrameController : AbstractEditFrameController<Ticket>() {

    @Autowired
    private lateinit var personRepository: PersonRepository
    @Autowired
    private lateinit var showRepository: ShowRepository


    private lateinit var ticket: Ticket
    private lateinit var idField: TextField
    private lateinit var personBox: ComboBox<Person>
    private lateinit var showBox: ComboBox<Show>


    override fun fieldsToEntity() = Ticket().apply {
        person = personBox.value
        show = showBox.value
    }

    override fun createInterfaceFields(gridPane: GridPane, nullableEntity: Ticket?) {
        ticket = nullableEntity ?: Ticket()
        idField = TextField("${ticket.id}").apply { isEditable = false }
        personBox = ComboBox<Person>().apply {
            val items = FXCollections.observableArrayList(personRepository.findAll())
            setItems(items)
            selectionModel.select(items.firstOrNull{it.id == ticket.person?.id})
        }
        showBox = ComboBox<Show>().apply {
            val items = FXCollections.observableArrayList(showRepository.findAll())
            setItems(items)
            selectionModel.select(items.firstOrNull{it == ticket.show})
        }
        gridPane.apply {
            addRow(1, Label("id"), idField)
            addRow(2, Label("person"), personBox)
            addRow(3, Label("show"), showBox)
        }
    }
}