package br.com.connectattoo.domain.use_cases.auth

import br.com.connectattoo.domain.base.BaseUseCase
import br.com.connectattoo.domain.base.DataResult
import br.com.connectattoo.domain.model.ClientData
import br.com.connectattoo.domain.model.TokenData
import br.com.connectattoo.domain.network.NetworkResult
import br.com.connectattoo.domain.repository.AuthRepository

class RegisterClientUseCase(
    private val repository: AuthRepository
) : BaseUseCase<ClientData, String>() {
    override suspend fun doWork(value: ClientData): DataResult<String> {
        return repository.registerClient(value).toDataResult()
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