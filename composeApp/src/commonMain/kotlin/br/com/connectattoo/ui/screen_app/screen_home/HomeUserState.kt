package br.com.connectattoo.ui.screen_app.screen_home

import br.com.connectattoo.domain.model.Tag

data class HomeUserState(
    val name: String = "",
    val username: String? = "",
    val birthDate: String? = "",
    val imageProfile: String? = "",
    val stateError: String? = "",
    val tags: List<Tag>? = emptyList(),
    val email: String? = "",
)
