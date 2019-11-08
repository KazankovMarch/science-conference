package ru.adkazankov.scienceconference.control

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.scene.layout.HBox
import org.springframework.stereotype.Controller
import javafx.scene.layout.VBox
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import java.net.URL
import java.util.*
import javax.annotation.PostConstruct

class MainFrameController {

    @Autowired
    private lateinit var tabControllers: List<AbstractTabController<*>>
    @FXML
    private lateinit var tabPane: TabPane

    @PostConstruct
    private fun init(){
        tabControllers.forEach {
            tabPane.tabs.add(Tab(it.name, it.content))
        }
    }

    private fun selectedTabController() =
            tabControllers.filter { tabPane.selectionModel.selectedItem.content == it.content }.first()

    @FXML
    fun onAddAction(event: ActionEvent) = selectedTabController().onAddAction(event)

    @FXML
    fun onDeleteAction(event: ActionEvent) = selectedTabController().onDeleteAction(event)

    @FXML
    fun onEditAction(event: ActionEvent) = selectedTabController().onEditAction(event)

    @FXML
    fun onFilterAction(event: ActionEvent) = selectedTabController().onFilterAction(event)

    @FXML
    fun onLoadAction(event: ActionEvent) = selectedTabController().onLoadAction(event)

    @FXML
    fun onRefreshAction(event: ActionEvent) = selectedTabController().onRefreshAction(event)

    @FXML
    fun onSaveAction(event: ActionEvent) = selectedTabController().onSaveAction(event)
}