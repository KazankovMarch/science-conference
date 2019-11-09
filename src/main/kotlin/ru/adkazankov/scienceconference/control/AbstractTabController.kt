package ru.adkazankov.scienceconference.control

import javafx.fxml.FXML
import javafx.scene.control.CheckBox
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.VBox
import org.springframework.data.jpa.repository.JpaRepository
import ru.adkazankov.scienceconference.control.edit.EditFrameController
import ru.adkazankov.scienceconference.util.showError
import java.lang.reflect.Field
import javax.annotation.PostConstruct


class AbstractTabController<T>: CrudController {

    lateinit var entityType: Class<T>
    lateinit var name: String
    lateinit var repository: JpaRepository<T, *>
    lateinit var editFrameController: EditFrameController<T>

    @FXML
    lateinit var content: AnchorPane
    @FXML
    private lateinit var showColumnVBox: VBox
    @FXML
    private lateinit var tableView: TableView<T>


    @PostConstruct
    fun postConstruct(){
        entityType.declaredFields.forEach {field ->
            val checkbox = CheckBox(field.name)
            checkbox.isSelected = true
            checkbox.selectedProperty().addListener {
                observable, oldValue, newValue -> setFieldVisible(newValue, field)
            }

            showColumnVBox.children.add(checkbox)

            val tableColumn: TableColumn<T, String> = TableColumn(field.name)
            tableColumn.cellValueFactory = PropertyValueFactory(field.name)
            tableView.columns.add(tableColumn)
        }
        onRefreshAction()
    }

    private fun setFieldVisible(newValue: Boolean, field: Field) {
        val column = tableView.columns.first { it.text == field.name }
        column.isVisible = newValue
    }

    private fun selectedEntity(): T? = tableView.selectionModel?.selectedItem

    override fun onAddAction() = editFrameController.newEntity()?.let{
        try{
            repository.save(it)
            onRefreshAction()
        }catch (e: Exception){
            showError(main = e.toString())
        }
    }

    override fun onDeleteAction() = selectedEntity()?.let {
        try{
            repository.delete(it)
            onRefreshAction()
        }catch (e: Exception){
            showError(main = e.toString())
        }
    }

    override fun onEditAction() = selectedEntity()?.let {
        try{
            editFrameController.editEntity(it)?.let {
                repository.save(it)
                onRefreshAction()
            }
        }catch (e: Exception){
            showError(main = e.toString())
        }
    }

    override fun onFilterAction() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoadAction() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRefreshAction() {
        tableView.items.clear()
        tableView.items.addAll(
                repository.findAll()
        )
        tableView.refresh()
    }

    override fun onSaveAction() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}