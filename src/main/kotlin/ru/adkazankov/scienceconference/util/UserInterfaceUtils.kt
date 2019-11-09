package ru.adkazankov.scienceconference.util

import javafx.scene.control.Alert
import javafx.scene.control.ButtonType

fun showError(main: String, context: String? = null) {
    val alert = Alert(Alert.AlertType.ERROR)
    alert.title = "Error"
    alert.headerText = main
    alert.contentText = context
    alert.show()
}

fun showConfirm(main: String, context: String? = null): Boolean {
    val alert = Alert(Alert.AlertType.CONFIRMATION)
    alert.title = "Confirm action"
    alert.headerText = main
    alert.contentText = context
    return alert.showAndWait().get() == ButtonType.OK
}

fun showInfo(main: String, context: String? = null): Boolean {
    val alert = Alert(Alert.AlertType.INFORMATION)
    alert.title = "Information"
    alert.headerText = main
    alert.contentText = context
    alert.showAndWait()
    return !alert.result.buttonData.isCancelButton
}