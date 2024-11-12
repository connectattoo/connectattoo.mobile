package br.com.connectattoo.domain.use_cases.auth

import br.com.connectattoo.domain.base.BaseUseCase
import br.com.connectattoo.domain.base.DataResult
import br.com.connectattoo.domain.model.ArtistData
import br.com.connectattoo.domain.model.TokenData
import br.com.connectattoo.domain.repository.AuthRepository

class RegisterTattooArtistUseCase(
    private val repository: AuthRepository
) : BaseUseCase<ArtistData, TokenData>() {
    override suspend fun doWork(value: ArtistData): DataResult<TokenData> {
        return repository.registerTattooArtist(artistData = value)
    }


}