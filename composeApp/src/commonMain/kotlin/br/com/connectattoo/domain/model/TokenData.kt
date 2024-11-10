package br.com.connectattoo.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class TokenData (
    val accessToken: String
)
