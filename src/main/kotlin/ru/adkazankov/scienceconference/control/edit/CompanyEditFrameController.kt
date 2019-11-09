package ru.adkazankov.scienceconference.control.edit

import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import org.springframework.stereotype.Controller
import ru.adkazankov.scienceconference.domain.Company

@Controller
class CompanyEditFrameController : EditFrameController<Company>() {

    private lateinit var company: Company
    private lateinit var nameField: TextField
    private lateinit var idField: TextField


    override fun fieldsToEntity(): Company {
        company.name = nameField.text
        return company    }

    override fun createInterfaceFields(gridPane: GridPane, nullableCompany: Company?) {
        this.company = nullableCompany ?: Company()
        idField = TextField("${company.id}").apply { isEditable = false }
        nameField = TextField(company.name)
        gridPane.addRow(1, Label("id"), idField)
        gridPane.addRow(2, Label("name"), nameField)
    }
}