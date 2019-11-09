package ru.adkazankov.scienceconference.control

interface CrudController {
    
    fun onAddAction(): Unit?

    fun onDeleteAction(): Unit?

    fun onEditAction(): Unit?

    fun onFilterAction() 

    fun onLoadAction()
    
    fun onRefreshAction()
    
    fun onSaveAction() 

}