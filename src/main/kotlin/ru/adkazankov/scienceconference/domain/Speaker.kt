package ru.adkazankov.scienceconference.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
open class Speaker {
    @Id
    open var personId: Long? = null
    @ManyToOne
    open var company: Company? = null

    override fun toString(): String {
        return "(personId=$personId, company=$company)"
    }
}