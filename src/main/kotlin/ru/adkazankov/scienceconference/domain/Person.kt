package ru.adkazankov.scienceconference.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
open class Person {
    @Id
    @GeneratedValue(generator = "person_id_seq")
    var id: Long? = null
    var fio: String? = null
    var email: String? = null
}
