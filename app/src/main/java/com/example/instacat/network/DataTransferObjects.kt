package com.example.cattestproject.network

import com.example.android.devbyteviewer.database.DatabaseCat
import com.example.android.devbyteviewer.domain.Cat
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkCat(
    val id: String,
    @Json(name = "url")
    val imgUrl: String,
    @Json(name = "source_url")
    val imgSrcUrl: String
)

fun List<NetworkCat>.asDomainModel(): List<Cat> {
    return this.map {
        Cat(
            id = it.id,
            title = "image $it.id",
            description = "This is the description for image $it.id, It's a really cool image, bask in it's gloriousness",
            url = it.imgUrl
        )
    }
}
//TODO refactor description to strings file
fun List<NetworkCat>.asDatabaseModel(): Array<DatabaseCat> {
    return this.map {
        DatabaseCat(
            id = it.id,
            title = "image ${it.id}",
            description = "This is the description for image ${it.id}, It's a really cool image, bask in it's gloriousness",
            url = it.imgUrl
        )
    }.toTypedArray()
}