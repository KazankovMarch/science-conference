package ru.adkazankov.scienceconference.control

import javafx.event.EventHandler
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import javafx.stage.FileChooser
import javafx.stage.Modality
import javafx.stage.Stage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import ru.adkazankov.scienceconference.util.Importer
import ru.adkazankov.scienceconference.util.exportToFile
import ru.adkazankov.scienceconference.util.showError

@Controller
class SaveLoadController {

    @Autowired
    private lateinit var importer: Importer

    private val delimiterField = TextField(";")
    private val fileChooser = FileChooser()

    fun <T> save(entityType: Class<T>, entities: List<T>){
        val okButton =  Button("export")
        okButton.onAction = EventHandler {
            if (delimiterField.text == "") {
                showError("Write delimiter")
            }
            else {
                val file = fileChooser.showSaveDialog(okButton.scene.window)
                exportToFile(file, entities, entityType, delimiterField.text)
                close()
            }
        }
        showAndWait(okButton)
    }
    fun <T> load(entityType: Class<T>) {
        val okButton =  Button("import")
        okButton.onAction = EventHandler {
            if (delimiterField.text == ""){
                showError("Write delimiter")
            } else{
                val file = fileChooser.showOpenDialog(okButton.scene.window)
                if(file!=null){
                    importer.importFromFile(file, entityType, delimiterField.text)
                    close()
                }
            }
        }
        showAndWait(okButton)
    }

    private fun close() = (delimiterField.scene.window as Stage).close()


    private fun showAndWait(okButton: Button) {
        delimiterField.text = ";"
        val gridPane = GridPane().apply {
            addRow(0, Label("Delimiter"), delimiterField)
            addRow(1, Label("File"), okButton)
        }
        val root = Group().apply {
            children.add(gridPane)
        }
        Stage().apply {
            this.title = title
            this.initModality(Modality.APPLICATION_MODAL)
            this.centerOnScreen()
            this.scene = Scene(root)
        }.showAndWait()
    }
}