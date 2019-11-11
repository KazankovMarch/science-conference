package ru.adkazankov.scienceconference.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
open class Speaker {
    @Id
    @GeneratedValue(generator = "speaker_person_id_seq")
    open var personId: Long? = null
    @ManyToOne
    open var company: Company? = null

    override fun toString(): String {
        return "($company-$personId)"
    }
}