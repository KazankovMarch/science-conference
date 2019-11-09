package ru.adkazankov.scienceconference.control

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.CheckBox
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.VBox
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Controller
import java.lang.reflect.Field
import java.net.URL
import java.util.*
import javax.annotation.PostConstruct


class AbstractTabController<T>: CrudController {

    lateinit var entityType: Class<T>
    lateinit var name: String
    lateinit var repository: JpaRepository<T, *>

    @FXML
    lateinit var content: AnchorPane
    @FXML
    private lateinit var showColumnVBox: VBox
    @FXML
    private lateinit var tableView: TableView<T>


    @PostConstruct
    fun postConstruct(){
        println("POSTCONSTRUCT")
        entityType.declaredFields.forEach {field ->
            val checkbox = CheckBox(field.name)
            checkbox.isSelected = true
            checkbox.selectedProperty().addListener {
                observable, oldValue, newValue -> setFieldVisible(newValue, field)
            }

            showColumnVBox.children.add(checkbox)

            val tableColumn: TableColumn<T, String> = TableColumn(field.name)
            tableColumn.setCellValueFactory ( PropertyValueFactory(field.name) )
            tableView.columns.add(tableColumn)
        }
    }

    private fun setFieldVisible(newValue: Boolean, field: Field) {
        val column = tableView.columns.first { it.text == field.name }
        column.isVisible = newValue
    }

    private fun selectedEntity(): T? = tableView.selectionModel.selectedItem

    override fun onAddAction(event: ActionEvent) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDeleteAction(event: ActionEvent) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onEditAction(event: ActionEvent) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFilterAction(event: ActionEvent) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoadAction(event: ActionEvent) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRefreshAction(event: ActionEvent) {
        tableView.items.clear()
        tableView.items.addAll(
                repository.findAll()
        )
        tableView.refresh()
    }

    override fun onSaveAction(event: ActionEvent) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}