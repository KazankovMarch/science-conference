package ru.adkazankov.scienceconference.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
open class Company {
    @Id
    @GeneratedValue(generator = "company_id_seq")
    open var id: Long? = null
    open var name: String? = null
    override fun toString(): String {
        return "Company(id=$id, name=$name)"
    }

}