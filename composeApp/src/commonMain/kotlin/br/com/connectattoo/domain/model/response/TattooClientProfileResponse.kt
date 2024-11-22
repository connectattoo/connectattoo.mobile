package br.com.connectattoo.domain.model.response

import br.com.connectattoo.domain.model.Gallery
import br.com.connectattoo.domain.model.Tag
import br.com.connectattoo.domain.model.toGalleryList
import br.com.connectattoo.domain.model.toTagEntity
import br.com.connectattoo.local.database.entity.TattooClientProfileEntity
import kotlinx.serialization.Serializable

@Serializable
data class TattooClientProfileResponse(
    val name: String? = "",
    val username: String? = "",
    val birthDate: String? = "",
    val imageProfile: String? = "",
    val tags: List<Tag> = emptyList(),
    val email: String? = "",
    val galleries: List<Gallery> = emptyList()
) {
    fun toTattooClientProfileEntity(): TattooClientProfileEntity {
        return TattooClientProfileEntity(
            name = this.name ?: "",
            username = this.username ?: "",
            birthDate = this.birthDate ?: "",
            imageProfile = this.imageProfile ?: "",
            tags = this.tags.toTagEntity(),
            email = this.email,
            galleries = this.galleries.toGalleryList()
        )
    }
}
