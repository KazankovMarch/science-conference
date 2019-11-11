package ru.adkazankov.scienceconference.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
open class Auditory {
    @Id
    @GeneratedValue(generator = "auditory_id_seq")
    open var id: Long? = null
    open var address: String? = null
    open var audienceMaxCount: Int? = null
    override fun toString(): String {
        return "(id=$id, address=$address, audienceMaxCount=$audienceMaxCount)"
    }

}