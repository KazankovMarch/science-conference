package ru.adkazankov.scienceconference.control

import javafx.beans.property.SimpleStringProperty
import javafx.beans.value.ObservableValue
import javafx.fxml.FXML
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.util.Callback
import org.springframework.beans.factory.annotation.Autowired
import ru.adkazankov.scienceconference.util.DbWork
import ru.adkazankov.scienceconference.util.showError

class SelectTabController {
    @Autowired
    private lateinit var dbWork: DbWork


    @FXML
    private lateinit var tableView: TableView<Map<Int, String>>
    @FXML
    private lateinit var selectField: TextField

    @FXML
    fun onExecuteAction() {
        if(selectField.text == "") return
        try {
            /*
            *
 ObservableList personsMapList = ...

 TableColumn<Map, String> firstNameColumn = new TableColumn<Map, String>("First Name");
 firstNameColumn.setCellValueFactory(new MapValueFactory<String>("firstName"));

 TableView table = new TableView(personMapList);
 tableView.getColumns().setAll(firstNameColumn)
            * */
            val resultSet = dbWork.executeQuery(selectField.text)
            tableView.items.clear()
            tableView.columns.clear()
            val meta = resultSet.metaData
            for(i in 1..meta.columnCount){
                val column = TableColumn<Map<Int, String>,String>(meta.getColumnName(i))
                column.cellValueFactory = object : Callback<TableColumn.CellDataFeatures<Map<Int, String>, String>, ObservableValue<String>> {
                    override fun call(param: TableColumn.CellDataFeatures<Map<Int, String>, String>?): ObservableValue<String> {
                        return SimpleStringProperty(param?.value?.get(i))
                    }
                }
                tableView.columns.add(column)

            }
            while (resultSet.next()){
                val map = HashMap<Int, String>(meta.columnCount)
                for(i in 1..meta.columnCount){
                    map.put(i, resultSet.getString(i))
                }
                tableView.items.add(map)
            }
        } catch (e: Exception){
            showError(e.toString())
        }
    }
}