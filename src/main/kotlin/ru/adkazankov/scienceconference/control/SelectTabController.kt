package ru.adkazankov.scienceconference.control

import javafx.beans.property.SimpleStringProperty
import javafx.beans.value.ObservableValue
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TextArea
import javafx.stage.FileChooser
import javafx.util.Callback
import org.springframework.beans.factory.annotation.Autowired
import ru.adkazankov.scienceconference.util.DbWork
import ru.adkazankov.scienceconference.util.showError
import ru.adkazankov.scienceconference.util.showInfo
import java.io.FileReader
import java.io.FileWriter

private val fileChooser = FileChooser()

class SelectTabController {
    @Autowired
    private lateinit var dbWork: DbWork

    @FXML
    private lateinit var tableView: TableView<Map<Int, String>>
    @FXML
    private lateinit var selectArea: TextArea

    @FXML
    fun onExecuteAction() {
        if (selectArea.text == "") return
        try {
            val resultSet = dbWork.executeQuery(selectArea.text)
            tableView.items.clear()
            tableView.columns.clear()
            val meta = resultSet.metaData
            for (i in 1..meta.columnCount) {
                val column = TableColumn<Map<Int, String>, String>(meta.getColumnName(i))
                column.cellValueFactory = object : Callback<TableColumn.CellDataFeatures<Map<Int, String>, String>, ObservableValue<String>> {
                    override fun call(param: TableColumn.CellDataFeatures<Map<Int, String>, String>?): ObservableValue<String> {
                        return SimpleStringProperty(param?.value?.get(i))
                    }
                }
                tableView.columns.add(column)

            }
            while (resultSet.next()) {
                val map = HashMap<Int, String>(meta.columnCount)
                for (i in 1..meta.columnCount) {
                    map.put(i, resultSet.getString(i))
                }
                tableView.items.add(map)
            }
        } catch (e: Exception) {
            showError(e.toString())
        }
    }


    @FXML
    fun onSaveAction(event: ActionEvent) {
        val file = fileChooser.showSaveDialog((event.source as Node).scene.window)
        if (file != null)
            FileWriter(file).use {
                it.write(selectArea.text)
                showInfo("Saving successful")
            }
    }

    @FXML
    fun onLoadAction(event: ActionEvent) = try {
        fileChooser.showOpenDialog((event.source as Node).scene.window)?.let {
            FileReader(it).use {reader ->
                selectArea.text = reader.readLines().stream().reduce { t, u ->
                    "$t\n$u"
                }.get()
            }
        }
    } catch (e: Exception) {
        showError("Cant read file", e.toString())
    }
}
