package br.com.connectattoo.domain.use_cases.auth

import br.com.connectattoo.domain.base.BaseUseCase
import br.com.connectattoo.domain.base.DataResult
import br.com.connectattoo.domain.model.ClientData
import br.com.connectattoo.domain.model.TokenData
import br.com.connectattoo.domain.network.NetworkResult
import br.com.connectattoo.domain.repository.AuthRepository
import br.com.connectattoo.util.PreferencesHelper

class ConfirmEmailUseCase(
    private val repository: AuthRepository,
    private val preferencesHelper: PreferencesHelper
) : BaseUseCase<String, String>() {
    override suspend fun doWork(value: String): DataResult<String> {
        val tokenData = preferencesHelper.getToken()
        return if (tokenData.isNotEmpty()){
            repository.confirmEmail(tokenData).toDataResult()
        }else{
            DataResult.Failure(Throwable(message = "Token não encontrado"))
        }
    }

    private fun NetworkResult<TokenData>.toDataResult(): DataResult<String> {
        return when (this) {
            is NetworkResult.Success -> {
                DataResult.Success(this.data.accessToken.toString())
            }

            is NetworkResult.Error -> {
                when (this.errorResponse?.message) {
                    "Token inválido." -> {
                        preferencesHelper.clearToken()
                        DataResult.Failure(Throwable(message = this.errorResponse.message))
                    }else ->{
                        DataResult.Failure(Throwable(message = this.errorResponse?.message))
                    }
                }
            }

            is NetworkResult.Exception -> {
                DataResult.Failure(this.e)
            }
        }
    }

}