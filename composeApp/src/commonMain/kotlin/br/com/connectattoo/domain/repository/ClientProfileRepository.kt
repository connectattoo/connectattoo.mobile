package br.com.connectattoo.domain.repository

import br.com.connectattoo.domain.model.response.TattooClientProfileResponse
import br.com.connectattoo.domain.network.NetworkResult

interface ClientProfileRepository {
    suspend fun getClientProfile(): NetworkResult<TattooClientProfileResponse>
}