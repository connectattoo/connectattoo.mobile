package br.com.connectattoo.domain.repository

import br.com.connectattoo.domain.base.DataResult
import br.com.connectattoo.domain.model.ArtistData
import br.com.connectattoo.domain.model.ClientData
import br.com.connectattoo.domain.model.TokenData

interface AuthRepository {
    suspend fun registerClient(clientData: ClientData): DataResult<TokenData>
    suspend fun registerTattooArtist(artistData: ArtistData): DataResult<TokenData>
}