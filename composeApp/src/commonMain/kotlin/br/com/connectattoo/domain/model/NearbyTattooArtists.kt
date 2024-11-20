package br.com.connectattoo.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class NearbyTattooArtists(
    val tattoo: String? = null,
    val name: String? = null,
    val assessment: String? = null,
    val address: String? = null,
    val profileImage: String? = null
)
