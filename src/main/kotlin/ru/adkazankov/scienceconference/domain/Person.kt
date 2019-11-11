package ru.adkazankov.scienceconference.domain

import org.springframework.transaction.annotation.Transactional
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
@Transactional
class Person {
    @Id
    @GeneratedValue(generator = "person_id_seq")
    var id: Long? = null
    var fio: String? = null
    var email: String? = null
    override fun toString(): String {
        return "$fio, email=$email id=$id"
    }
}
