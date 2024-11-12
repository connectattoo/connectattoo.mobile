package br.com.connectattoo.domain.use_cases.auth

import br.com.connectattoo.domain.base.BaseUseCase
import br.com.connectattoo.domain.base.DataResult
import br.com.connectattoo.domain.model.ClientData
import br.com.connectattoo.domain.model.TokenData
import br.com.connectattoo.domain.repository.AuthRepository

class RegisterClientUseCase(
    private val repository: AuthRepository
) : BaseUseCase<ClientData, TokenData>() {
    override suspend fun doWork(value: ClientData): DataResult<TokenData> {
        return repository.registerClient(value)

    }

}