package ru.adkazankov.scienceconference.util

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.File
import java.io.FileWriter
import java.lang.reflect.Field
import java.util.*
import javax.persistence.Id

@Component
class Importer {

    @Autowired
    private lateinit var dbWork: DbWork


    fun <T> importFromFile(file: File, entityType: Class<T>, delimiter: String) = Scanner(file).use { scanner ->
        while (scanner.hasNext()) {
            val line = scanner.nextLine()
            val fields = line.subSequence(0, line.length - 1).split(delimiter)
            val sqlBuilder = StringBuilder("INSERT INTO ${entityType.name.split(".").last()} VALUES (")
            fields.forEach {
                val field = if (it.isEmpty()) {
                    if (sqlBuilder.last() == '(') {
                        "DEFAULT"
                    } else {
                        null
                    }
                } else {
                    "\'$it\'"
                }
                sqlBuilder.append("$field,")
            }
            sqlBuilder.setLength(sqlBuilder.length - 1)
            sqlBuilder.append(")")
            dbWork.executeUpdate(sqlBuilder.toString())
        }
    }
}


fun <T> exportToFile(
        file: File, entities: List<T>, entityType: Class<T>, delimiter: String
) = FileWriter(file).use { writer ->
    entities.forEach { entity ->
        writer.write(serializeEntity(entity, entityType, delimiter))
        writer.write("\n")
    }
}


private fun <T> serializeEntity(entity: T, entityType: Class<T>, delimiter: String): String {
    val builder = StringBuilder()
    entityType.declaredFields.forEach {
        it.isAccessible = true
        if (it.type.isCustom()) {
            builder.append(it.getId(entity))
        } else {
            builder.append(it.get(entity)?.toString())
        }
        builder.append(delimiter)
    }
    return builder.toString()
}

private fun Class<*>.isCustom(): Boolean {
    declaredFields.forEach {
        val id = it.getAnnotation(Id::class.java)
        if (id != null) return true
    }
    return false
}


private fun <T> Field.getId(entity: T): Long {
    this.isAccessible = true
    type.declaredFields.forEach {
        it.isAccessible = true
        val id = it.getAnnotation(Id::class.java)
        if (id != null) return it.get(this.get(entity)) as Long
    }
    throw IllegalArgumentException()
}