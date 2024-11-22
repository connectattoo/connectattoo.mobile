package br.com.connectattoo.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Gallery(
    val id: String? = "",
    val name: String? = ""
)

fun List<Gallery>.toGalleryList(): List<Gallery> =
    this.map {
        it
    }