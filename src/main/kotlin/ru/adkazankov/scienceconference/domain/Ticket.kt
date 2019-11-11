package ru.adkazankov.scienceconference.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
open class Ticket {
    @Id
    open var id: Long? = null
    @ManyToOne
    open var person: Person? = null
    @ManyToOne
    open var presentation: Presentation? = null
}