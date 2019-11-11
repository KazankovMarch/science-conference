package ru.adkazankov.scienceconference.control.edit

import javafx.collections.FXCollections
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import ru.adkazankov.scienceconference.control.DateTimePicker
import ru.adkazankov.scienceconference.domain.Auditory
import ru.adkazankov.scienceconference.domain.Presentation
import ru.adkazankov.scienceconference.domain.Speaker
import ru.adkazankov.scienceconference.repository.AuditoryRepository
import ru.adkazankov.scienceconference.repository.SpeakerRepository

@Controller
class PresentationEditFrameController : AbstractEditFrameController<Presentation>() {

    @Autowired
    private lateinit var speakerRepository: SpeakerRepository
    @Autowired
    private lateinit var auditoryRepository: AuditoryRepository

    private lateinit var presentation: Presentation
    private lateinit var idField: TextField
    private lateinit var titleField: TextField
    private lateinit var speakerBox: ComboBox<Speaker>
    private lateinit var timeStartField: DateTimePicker
    private lateinit var timeEndField: DateTimePicker
    private lateinit var auditoryBox: ComboBox<Auditory>


    override fun fieldsToEntity() = presentation.apply {
        title = titleField.text
        speaker = speakerBox.value
        timeStart = timeStartField.dateTimeValueProperty().value
        timeEnd = timeEndField.dateTimeValueProperty().value
        auditory = auditoryBox.value
    }

    override fun createInterfaceFields(gridPane: GridPane, nullableEntity: Presentation?) {
        presentation = nullableEntity ?: Presentation()
        idField = TextField("${presentation.id}").apply { isEditable = false }
        titleField = TextField(presentation.title)
        speakerBox = ComboBox<Speaker>().apply {
            val items = FXCollections.observableArrayList(speakerRepository.findAll())
            setItems(items)
            selectionModel.select(items.firstOrNull{it.personId == presentation.speaker?.personId})
        }
        timeStartField = DateTimePicker().apply { dateTimeValueProperty().value = presentation.timeStart }
        timeEndField = DateTimePicker().apply { dateTimeValueProperty().value = presentation.timeEnd }
        auditoryBox = ComboBox<Auditory>().apply {
            val items = FXCollections.observableArrayList(auditoryRepository.findAll())
            setItems(items)
            selectionModel.select(items.firstOrNull{it.id == presentation.auditory?.id})
        }
        gridPane.apply {
            addRow(1, Label("id"), idField)
            addRow(2, Label("title"), titleField)
            addRow(3, Label("speaker"), speakerBox)
            addRow(4, Label("time start"), timeStartField)
            addRow(5, Label("time end"), timeEndField)
            addRow(6, Label("auditory"), auditoryBox)
        }
    }

}