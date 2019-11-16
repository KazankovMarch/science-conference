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
        return "$id"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Auditory

        if (id != other.id) return false
        if (address != other.address) return false
        if (audienceMaxCount != other.audienceMaxCount) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (address?.hashCode() ?: 0)
        result = 31 * result + (audienceMaxCount ?: 0)
        return result
    }

}