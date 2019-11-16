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
        return "$id"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person

        if (id != other.id) return false
        if (fio != other.fio) return false
        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (fio?.hashCode() ?: 0)
        result = 31 * result + (email?.hashCode() ?: 0)
        return result
    }


}
