package br.com.connectattoo.domain.use_cases.client

import br.com.connectattoo.domain.base.BaseUseCase
import br.com.connectattoo.domain.base.DataResult
import br.com.connectattoo.domain.model.TattooClientProfile
import br.com.connectattoo.domain.model.response.TattooClientProfileResponse
import br.com.connectattoo.domain.network.NetworkResult
import br.com.connectattoo.domain.repository.ClientProfileRepository
import br.com.connectattoo.local.database.dao.TattooClientProfileDao

class GetClientProfileUseCase(
    private val repository: ClientProfileRepository,
    private val tattooClientProfileDao: TattooClientProfileDao
) : BaseUseCase<Unit, TattooClientProfile>() {
    override suspend fun doWork(value: Unit): DataResult<TattooClientProfile> {

        return try {
            val networkResult = repository.getClientProfile()
            handleNetworkResult(networkResult)
        } catch (e: Exception) {
            val localData = tattooClientProfileDao.getTattooClientProfile()
            if (localData != null) {
                DataResult.Success(localData.toTattooClientProfile())
            } else {
                DataResult.Failure(e)
            }
        }
    }

    private suspend fun handleNetworkResult(
        networkResult: NetworkResult<TattooClientProfileResponse>
    ): DataResult<TattooClientProfile> {
        return when (networkResult) {
            is NetworkResult.Success -> {
                tattooClientProfileDao.deleteTattooClientProfile()
                tattooClientProfileDao.insertTattooClientProfile(networkResult.data.toTattooClientProfileEntity())

                val updatedData = tattooClientProfileDao.getTattooClientProfile()
                DataResult.Success(updatedData?.toTattooClientProfile() ?: TattooClientProfile())
            }

            is NetworkResult.Error -> {
                val localData = tattooClientProfileDao.getTattooClientProfile()
                DataResult.Success(localData?.toTattooClientProfile() ?: TattooClientProfile())
            }

            is NetworkResult.Exception -> {
                val localData = tattooClientProfileDao.getTattooClientProfile()
                DataResult.Success(localData?.toTattooClientProfile() ?: TattooClientProfile())
            }
        }
    }

}