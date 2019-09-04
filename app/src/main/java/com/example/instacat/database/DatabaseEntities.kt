package com.example.android.devbyteviewer.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.devbyteviewer.domain.Cat

@Entity
data class DatabaseCat constructor(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val url: String
)

fun List<DatabaseCat>.asDomainModel(): List<Cat> {
    return map {
        Cat(
            id = it.id,
            title = it.title,
            description = it.description,
            url = it.url
        )
    }
}