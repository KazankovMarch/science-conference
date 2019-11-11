package ru.adkazankov.scienceconference.domain

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
open class Presentation {
    @Id
    @GeneratedValue(generator = "presentation_id_seq")
    open var id: Long? = null
    open var title: String? = null
    @ManyToOne
    open var speaker: Speaker? = null
    open var timeStart: LocalDateTime? = null
    open var timeEnd: LocalDateTime? = null
    @ManyToOne
    open var auditory: Auditory? = null

    override fun toString(): String {
        return "$title, speaker=$speaker,time: $timeStart--$timeEnd, auditory=$auditory id=$id"
    }

}
