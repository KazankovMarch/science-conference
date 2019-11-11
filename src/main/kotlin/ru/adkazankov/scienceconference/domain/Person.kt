package ru.adkazankov.scienceconference.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
open class Person {
    @Id
    @GeneratedValue(generator = "person_id_seq")
    open var id: Long? = null
    open var fio: String? = null
    open var email: String? = null
    override fun toString(): String {
        return "(id=$id, fio=$fio, email=$email)"
    }
}
