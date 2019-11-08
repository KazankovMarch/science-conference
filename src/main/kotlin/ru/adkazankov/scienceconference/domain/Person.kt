package ru.adkazankov.scienceconference.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
open class Person {
    @Id
    var id: Long? = null
    var fio: String? = null
    var email: String? = null
}
