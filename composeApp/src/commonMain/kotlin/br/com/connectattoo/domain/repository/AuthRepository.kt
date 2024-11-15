package br.com.connectattoo.domain.repository

import br.com.connectattoo.domain.base.DataResult
import br.com.connectattoo.domain.model.ArtistData
import br.com.connectattoo.domain.model.ClientData
import br.com.connectattoo.domain.model.TokenData
import br.com.connectattoo.domain.network.NetworkResult

interface AuthRepository {
    suspend fun registerClient(clientData: ClientData): NetworkResult<TokenData>
    suspend fun confirmEmail(token: String): NetworkResult<TokenData>
    suspend fun registerTattooArtist(artistData: ArtistData): DataResult<TokenData>
}