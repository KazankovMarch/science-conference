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
    open var presentation: Presentation? = null

    override fun toString(): String {
        return "$person, presentation=$presentation, id=$id"
    }

}