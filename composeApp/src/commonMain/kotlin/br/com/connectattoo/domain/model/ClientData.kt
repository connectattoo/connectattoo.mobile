package br.com.connectattoo.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ClientData (
    val name: String,
    val email: String,
    val password: String,
    val birthDate: String,
    val termsAccepted: Boolean
)