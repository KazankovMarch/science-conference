package ru.adkazankov.scienceconference.control

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Tab
import org.springframework.stereotype.Controller
import javafx.scene.layout.VBox



@Controller
class MainFrameController {
    @FXML
    private lateinit var presentationTab: Tab
    @FXML
    private lateinit var personTab: Tab
    @FXML
    private lateinit var speakerTab: Tab
    @FXML
    private lateinit var auditoryTab: Tab
    @FXML
    private lateinit var ticketTab: Tab
    @FXML
    private lateinit var queryTab: Tab
    @FXML
    private lateinit var showColumnVBox: VBox

    @FXML
    fun onAddAction(event: ActionEvent) {

    }

    @FXML
    fun onDeleteAction(event: ActionEvent) {

    }

    @FXML
    fun onEditAction(event: ActionEvent) {

    }

    @FXML
    fun onFilterAction(event: ActionEvent) {

    }

    @FXML
    fun onLoadAction(event: ActionEvent) {

    }

    @FXML
    fun onRefreshAction(event: ActionEvent) {

    }

    @FXML
    fun onSaveAction(event: ActionEvent) {

    }
}