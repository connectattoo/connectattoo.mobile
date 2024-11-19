package br.com.connectattoo.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenData (
    val accessToken: String? = null
)
