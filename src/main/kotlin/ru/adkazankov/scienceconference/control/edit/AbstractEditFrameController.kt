package ru.adkazankov.scienceconference.control.edit

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.GridPane
import javafx.stage.Modality
import javafx.stage.Stage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import ru.adkazankov.scienceconference.control.ControllerConfiguration
import ru.adkazankov.scienceconference.util.showError
import java.lang.reflect.Field


abstract class AbstractEditFrameController<T> {

    @Autowired
    @Qualifier("mainFrame")
    private lateinit var view: ControllerConfiguration.View

    private val fieldMap: MutableMap<Field, TextField> = HashMap()

    private var entity: T? = null

    private fun cancel(event: ActionEvent) = close(event)

    private fun save(it: ActionEvent) {
        if (isFullInputData()) {
            try {
                entity = fieldsToEntity()
                close(it)
            } catch (ex: NumberFormatException) {
                showError("Не удалось распознать числа", null)
            }
        } else {
            showError("Пожалуйста, заполните все поля", null)
        }
    }

    abstract fun fieldsToEntity(): T

    private fun isFullInputData(): Boolean {
        fieldMap.values.forEach {
            if (it.text == null || it.text == "") {
                return false
            }
        }
        return true
    }

    fun  newEntity(): T? = editEntity(null)

    fun  editEntity(entity: T?): T? {
        val saveButton = Button("save").apply {
            onAction = EventHandler { save(it) }
        }
        val cancelButton = Button("cancel").apply {
            onAction = EventHandler { cancel(it) }
        }
        val gridPane = GridPane().apply {
            addColumn(0, cancelButton)
            addColumn(1, saveButton)
            createInterfaceFields(this, entity)
        }
        val root = AnchorPane().apply {
            children.add(gridPane)
        }
        Stage().apply {
            title = entity?.toString() ?: "New Entity"
            scene = Scene(root)
            isResizable = true
            centerOnScreen()
            initModality(Modality.APPLICATION_MODAL)
        }.showAndWait()
        return this.entity
    }

    abstract fun createInterfaceFields(gridPane: GridPane, nullableEntity: T?)

    private fun close(event: ActionEvent) {
        ((event.source as Button).scene.window as Stage).close()
    }

}

