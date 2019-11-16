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
import ru.adkazankov.scienceconference.domain.Person
import ru.adkazankov.scienceconference.domain.Presentation
import ru.adkazankov.scienceconference.domain.Show
import ru.adkazankov.scienceconference.repository.AuditoryRepository
import ru.adkazankov.scienceconference.repository.PersonRepository
import ru.adkazankov.scienceconference.repository.PresentationRepository

@Controller
class ShowEditFrameController : AbstractEditFrameController<Show>() {

    @Autowired
    private lateinit var personRepository: PersonRepository
    @Autowired
    private lateinit var auditoryRepository: AuditoryRepository
    @Autowired
    private lateinit var presentationRepository: PresentationRepository

    private lateinit var show: Show
    private lateinit var idField: TextField
    private lateinit var presentationBox: ComboBox<Presentation>
    private lateinit var speakerBox: ComboBox<Person>
    private lateinit var timeStartField: DateTimePicker
    private lateinit var timeEndField: DateTimePicker
    private lateinit var auditoryBox: ComboBox<Auditory>


    override fun fieldsToEntity() = show.apply {
        speaker = speakerBox.value
        timeStart = timeStartField.dateTimeValueProperty().value
        timeEnd = timeEndField.dateTimeValueProperty().value
        auditory = auditoryBox.value
    }

    override fun createInterfaceFields(gridPane: GridPane, nullableEntity: Show?) {
        show = nullableEntity ?: Show()
        idField = TextField("${show.id}").apply { isEditable = false }

        presentationBox = ComboBox<Presentation>().apply {
            val items = FXCollections.observableArrayList(presentationRepository.findAll())
            setItems(items)
            selectionModel.select(items.firstOrNull{it == show.presentation})
        }
        speakerBox = ComboBox<Person>().apply {
            val items = FXCollections.observableArrayList(personRepository.findAll())
            setItems(items)
            selectionModel.select(items.firstOrNull{it == show.speaker})
        }
        auditoryBox = ComboBox<Auditory>().apply {
            val items = FXCollections.observableArrayList(auditoryRepository.findAll())
            setItems(items)
            selectionModel.select(items.firstOrNull{it.id == show.auditory?.id})
        }

        timeStartField = DateTimePicker().apply { dateTimeValueProperty().value = show.timeStart }
        timeEndField = DateTimePicker().apply { dateTimeValueProperty().value = show.timeEnd }

        gridPane.apply {
            addRow(1, Label("id"), idField)
            addRow(2, Label("presentation"), presentationBox)
            addRow(3, Label("person"), speakerBox)
            addRow(6, Label("auditory"), auditoryBox)
            addRow(4, Label("time start"), timeStartField)
            addRow(5, Label("time end"), timeEndField)
        }
    }

}