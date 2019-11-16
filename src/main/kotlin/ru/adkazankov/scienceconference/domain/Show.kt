package ru.adkazankov.scienceconference.domain

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
open class Show {
    @Id
    @GeneratedValue(generator = "show_id_seq")
    open var id: Long? = null
    @ManyToOne
    open var presentation: Presentation? = null
    @ManyToOne
    open var speaker: Person? = null
    @ManyToOne
    open var auditory: Auditory? = null

    open var timeStart: LocalDateTime? = null
    open var timeEnd: LocalDateTime? = null

    override fun toString(): String {
        return "$id"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Show

        if (id != other.id) return false
        if (presentation != other.presentation) return false
        if (speaker != other.speaker) return false
        if (auditory != other.auditory) return false
        if (timeStart != other.timeStart) return false
        if (timeEnd != other.timeEnd) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (presentation?.hashCode() ?: 0)
        result = 31 * result + (speaker?.hashCode() ?: 0)
        result = 31 * result + (auditory?.hashCode() ?: 0)
        result = 31 * result + (timeStart?.hashCode() ?: 0)
        result = 31 * result + (timeEnd?.hashCode() ?: 0)
        return result
    }

}