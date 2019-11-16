package ru.adkazankov.scienceconference.control

import javafx.fxml.FXML
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.scene.control.ToolBar
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import javax.annotation.PostConstruct

class MainFrameController {

    @Autowired
    private lateinit var entityTabControllers: List<AbstractEntityTabController<*>>
    @Autowired
    @Qualifier("selectTab")
    private lateinit var selectView: ControllerConfiguration.View
    @FXML
    private lateinit var tabPane: TabPane
    @FXML
    private lateinit var queryTab: Tab
    @FXML
    private lateinit var toolBar: ToolBar

    @PostConstruct
    private fun init(){
        entityTabControllers.forEach {
            tabPane.tabs.add(Tab(it.name, it.content))
        }
        queryTab.content = selectView.parent
        queryTab.setOnSelectionChanged { toolBar.isDisable = queryTab.isSelected }
        toolBar.isDisable = queryTab.isSelected
    }

    private fun selectedTabController() =
            entityTabControllers.filter { tabPane.selectionModel.selectedItem.content == it.content }.firstOrNull()

    @FXML
    fun onAddAction() = selectedTabController()?.onAddAction()
    @FXML
    fun onDeleteAction() = selectedTabController()?.onDeleteAction()
    @FXML
    fun onEditAction() = selectedTabController()?.onEditAction()
    @FXML
    fun onFilterAction() = selectedTabController()?.onFilterAction()
    @FXML
    fun onLoadAction() = selectedTabController()?.onLoadAction()
    @FXML
    fun onRefreshAction() = selectedTabController()?.onRefreshAction()
    @FXML
    fun onSaveAction() = selectedTabController()?.onSaveAction()
}