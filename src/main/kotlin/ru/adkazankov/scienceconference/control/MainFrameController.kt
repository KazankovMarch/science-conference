package ru.adkazankov.scienceconference.control

import javafx.fxml.FXML
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import org.springframework.beans.factory.annotation.Autowired
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
    fun onAddAction() = selectedTabController().onAddAction()
    @FXML
    fun onDeleteAction() = selectedTabController().onDeleteAction()
    @FXML
    fun onEditAction() = selectedTabController().onEditAction()
    @FXML
    fun onFilterAction() = selectedTabController().onFilterAction()
    @FXML
    fun onLoadAction() = selectedTabController().onLoadAction()
    @FXML
    fun onRefreshAction() = selectedTabController().onRefreshAction()
    @FXML
    fun onSaveAction() = selectedTabController().onSaveAction()
}