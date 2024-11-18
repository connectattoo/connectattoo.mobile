package br.com.connectattoo.domain.use_cases.auth

import br.com.connectattoo.domain.base.BaseUseCase
import br.com.connectattoo.domain.base.DataResult
import br.com.connectattoo.domain.model.ArtistData
import br.com.connectattoo.domain.model.TokenData
import br.com.connectattoo.domain.network.NetworkResult
import br.com.connectattoo.domain.repository.AuthRepository
import kotlinx.serialization.json.Json

class RegisterTattooArtistUseCase(
    private val repository: AuthRepository
) : BaseUseCase<ArtistData, String>() {
    override suspend fun doWork(value: ArtistData): DataResult<String> {
        return repository.registerTattooArtist(artistData = value).toDataResult()
    }

    private fun NetworkResult<TokenData>.toDataResult(): DataResult<String> {
        return when (this) {
            is NetworkResult.Success -> {
                DataResult.Success(this.data.accessToken.toString())
            }

            is NetworkResult.Error -> {
                DataResult.Failure(Throwable(message = this.errorResponse?.message))
            }

            is NetworkResult.Exception -> {
                DataResult.Failure(this.e)
            }
        }
    }
}