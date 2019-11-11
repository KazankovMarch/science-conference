package ru.adkazankov.scienceconference.control.edit

import javafx.collections.FXCollections
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
import javafx.scene.layout.GridPane
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Controller
import ru.adkazankov.scienceconference.domain.Company
import ru.adkazankov.scienceconference.domain.Person
import ru.adkazankov.scienceconference.domain.Speaker

@Controller
class SpeakerEditFrameController : AbstractEditFrameController<Speaker>() {

    @Autowired
    private lateinit var companyRepository: JpaRepository<Company, Long>
    @Autowired
    private lateinit var personRepository: JpaRepository<Person, Long>

    private lateinit var speaker: Speaker
    private lateinit var personBox: ComboBox<Person>
    private lateinit var companyBox: ComboBox<Company>

    override fun fieldsToEntity(): Speaker {
        speaker.company = companyBox.value
        speaker.personId = personBox.value.id
        return speaker
    }

    override fun createInterfaceFields(gridPane: GridPane, nullableEntity: Speaker?) {
        speaker = nullableEntity ?: Speaker()
        personBox = ComboBox(FXCollections.observableArrayList(personRepository.findAll()))
        if(nullableEntity!=null)
            personBox.selectionModel.select(personRepository.getOne(speaker.personId!!))
        gridPane.addRow(1, Label("person"), personBox)
        companyBox = ComboBox(FXCollections.observableArrayList(companyRepository.findAll()))
        if(nullableEntity!=null)
            companyBox.selectionModel.select(speaker.company)
        gridPane.addRow(2, Label("company"), companyBox)
    }
}