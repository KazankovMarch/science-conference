package ru.adkazankov.scienceconference.control

import javafx.fxml.FXML
import javafx.scene.control.Tab
import org.springframework.stereotype.Controller

@Controller
class MainFrameController {
    @FXML
    private lateinit var presentationTab: Tab

    @FXML
    private lateinit var personTab: Tab

    @FXML
    private lateinit var auditoryTab: Tab

    @FXML
    private lateinit var queryTab: Tab
}