package ru.adkazankov.scienceconference.control.edit

import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import org.springframework.stereotype.Controller
import ru.adkazankov.scienceconference.domain.Person

@Controller
class PersonEditFrameController : EditFrameController<Person>() {

    private lateinit var person: Person
    private lateinit var emailField: TextField
    private lateinit var fioField: TextField
    private lateinit var idField: TextField

    override fun fieldsToEntity(): Person {
        person.email = emailField.text
        person.fio = fioField.text
        return person
    }

    override fun createInterfaceFields(gridPane: GridPane, nullablePerson: Person?) {
        this.person = nullablePerson ?: Person()
        idField = TextField("${person.id}").apply { isEditable = false }
        fioField = TextField(person.fio)
        emailField = TextField(person.email)
        gridPane.addRow(1, Label("id"), idField)
        gridPane.addRow(2, Label("fio"), fioField)
        gridPane.addRow(3, Label("email"), emailField)
    }
}