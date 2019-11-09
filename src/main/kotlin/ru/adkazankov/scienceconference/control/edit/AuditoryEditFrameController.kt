package ru.adkazankov.scienceconference.control.edit

import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import org.springframework.stereotype.Controller
import ru.adkazankov.scienceconference.domain.Auditory

@Controller
class AuditoryEditFrameController : EditFrameController<Auditory>() {

    private lateinit var audienceMaxCountField: TextField
    private lateinit var addressField: TextField
    private lateinit var idField: TextField

    private lateinit var auditory: Auditory

    override fun fieldsToEntity(): Auditory {
        auditory.address = addressField.text
        auditory.audienceMaxCount = Integer.parseInt(audienceMaxCountField.text)
        return auditory
    }

    override fun createInterfaceFields(gridPane: GridPane, nullableAuditory: Auditory?) {
        this.auditory = nullableAuditory ?: Auditory()
        idField = TextField("${auditory.id}").apply { isEditable = false }
        addressField = TextField(auditory.address)
        audienceMaxCountField = TextField("${auditory.audienceMaxCount?:0}")
        gridPane.addRow(1, Label("id"), idField)
        gridPane.addRow(2, Label("address"), addressField)
        gridPane.addRow(3, Label("audience max count"), audienceMaxCountField)
    }
}