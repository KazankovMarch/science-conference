package ru.adkazankov.scienceconference.control.edit

import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import org.springframework.stereotype.Controller
import ru.adkazankov.scienceconference.domain.Presentation

@Controller
class PresentationEditFrameController : AbstractEditFrameController<Presentation>() {
    private lateinit var presentation: Presentation

    private lateinit var idField: TextField
    private lateinit var titleField: TextField
    private lateinit var subjectField: TextField


    override fun fieldsToEntity(): Presentation = Presentation().apply {
        this.title = titleField.text
        this.subject = subjectField.text
    }

    override fun createInterfaceFields(gridPane: GridPane, nullableEntity: Presentation?) {
        presentation = nullableEntity ?: Presentation()
        idField = TextField("${presentation.id}").apply { isEditable = false }
        titleField = TextField(presentation.title)
        subjectField = TextField(presentation.subject)
        gridPane.apply {
            addRow(1, Label("id"), idField)
            addRow(2, Label("title"), titleField)
            addRow(2, Label("title"), subjectField)
        }
    }
}