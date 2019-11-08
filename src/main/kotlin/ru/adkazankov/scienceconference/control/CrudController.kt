package ru.adkazankov.scienceconference.control

import javafx.event.ActionEvent

interface CrudController {
    
    fun onAddAction(event: ActionEvent) 

    fun onDeleteAction(event: ActionEvent) 

    fun onEditAction(event: ActionEvent) 

    fun onFilterAction(event: ActionEvent) 

    fun onLoadAction(event: ActionEvent)
    
    fun onRefreshAction(event: ActionEvent)
    
    fun onSaveAction(event: ActionEvent) 

}