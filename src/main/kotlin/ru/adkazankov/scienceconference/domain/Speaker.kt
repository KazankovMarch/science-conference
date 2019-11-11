package ru.adkazankov.scienceconference.domain

import org.springframework.transaction.annotation.Transactional
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
@Transactional
class Speaker {
    @Id
    var personId: Long? = null
    @ManyToOne
    var company: Company? = null

    override fun toString(): String {
        return "($company-$personId)"
    }
}