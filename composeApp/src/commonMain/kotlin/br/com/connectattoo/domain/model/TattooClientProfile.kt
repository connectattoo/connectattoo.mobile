package br.com.connectattoo.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class TattooClientProfile(
    val name: String? = "",
    val username: String? = "",
    val birthDate: String? = "",
    val imageProfile: String? = "",
    val tags: List<Tag> = emptyList(),
    val email: String? = "",
    val galleries: List<Gallery> = emptyList()
)