package br.com.connectattoo.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AddressData (
    val street: String,
    val number: String,
    val city: String,
    val state: String,
    val country: String = "B",
    val zipCode: String
)
