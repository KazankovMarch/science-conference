package ru.adkazankov.scienceconference.control

import javafx.event.ActionEvent
import org.springframework.beans.factory.annotation.Autowired
import ru.adkazankov.scienceconference.domain.Person
import javax.persistence.EntityManager

private const val TAB_NAME = "Участники"

class PersonTabController: AbstractTabController<Person>() {
    override val entityType = Person::class.java
    override val name: String = TAB_NAME

}