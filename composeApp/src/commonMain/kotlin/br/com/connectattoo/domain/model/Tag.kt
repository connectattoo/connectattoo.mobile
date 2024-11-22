package br.com.connectattoo.domain.model

import br.com.connectattoo.local.database.entity.TagEntity
import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    val id: String? = "",
    val name: String? = "",
    val isTagFiltered: Boolean = false
) {
    fun toTagEntity(): TagEntity = TagEntity(
        id = id,
        name = name
    )
}

fun List<Tag>.toTagEntity(): List<TagEntity> =
    this.map {
        it.toTagEntity()
    }
