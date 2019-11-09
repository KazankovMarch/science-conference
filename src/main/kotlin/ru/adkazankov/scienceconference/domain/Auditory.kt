package ru.adkazankov.scienceconference.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
open class Auditory {
    @Id
    var id: Long? = null
    var address: String? = null
    var audienceMaxCount: Int? = null
}