package ru.adkazankov.scienceconference.domain

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
open class Presentation {
    @Id
    open var id: Long? = null
    open var title: String? = null
    @ManyToOne
    open var speaker: Speaker? = null
    open var timeStart: LocalDateTime? = null
    open var timeEnd: LocalDateTime? = null
    @ManyToOne
    open var auditory: Auditory? = null

    override fun toString(): String {
        return "(id=$id, title=$title, speaker=$speaker, timeStart=$timeStart, timeEnd=$timeEnd, auditory=$auditory)"
    }

}
