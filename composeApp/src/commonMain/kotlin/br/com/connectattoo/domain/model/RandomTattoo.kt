package br.com.connectattoo.domain.model

data class RandomTattoo(
    val tattoo: String? = null,
    val name: String? = null,
    val profileImage: String? = null,
    var like: Boolean,
    var save:Boolean
)
