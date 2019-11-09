package ru.adkazankov.scienceconference.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
open class Auditory {
    @Id
    @GeneratedValue(generator = "auditory_id_seq")
    var id: Long? = null
    var address: String? = null
    var audienceMaxCount: Int? = null
}