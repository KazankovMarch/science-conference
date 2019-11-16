package ru.adkazankov.scienceconference.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
open class Presentation {
    @Id
    @GeneratedValue(generator = "presentation_id_seq")
    open var id: Long? = null
    open var title: String? = null
    open var subject: String? = null

    override fun toString(): String {
        return "$id"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Presentation

        if (id != other.id) return false
        if (title != other.title) return false
        if (subject != other.subject) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (subject?.hashCode() ?: 0)
        return result
    }


}
