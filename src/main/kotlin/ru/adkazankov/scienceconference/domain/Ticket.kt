package ru.adkazankov.scienceconference.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
open class Ticket {
    @Id
    @GeneratedValue(generator = "ticket_id_seq")
    open var id: Long? = null
    @ManyToOne
    open var person: Person? = null
    @ManyToOne
    open var show: Show? = null

    override fun toString(): String {
        return "$id"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Ticket

        if (id != other.id) return false
        if (person != other.person) return false
        if (show != other.show) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (person?.hashCode() ?: 0)
        result = 31 * result + (show?.hashCode() ?: 0)
        return result
    }


}