package br.com.connectattoo.data.repository

import br.com.connectattoo.domain.model.response.TattooClientProfileResponse
import br.com.connectattoo.domain.network.NetworkResult
import br.com.connectattoo.domain.repository.ClientProfileRepository
import br.com.connectattoo.util.PreferencesHelper
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpHeaders
import io.ktor.http.isSuccess

class ClientProfileRepositoryImpl(
    private val client: HttpClient,
    private val baseURL: String,
    private val preferencesHelper: PreferencesHelper
) : ClientProfileRepository {
    override suspend fun getClientProfile(): NetworkResult<TattooClientProfileResponse> {
        val tokenData = preferencesHelper.getToken()

        return try {
            val response: HttpResponse = client.get("$baseURL/profile/me") {
                headers {
                    append(HttpHeaders.Authorization, "Bearer $tokenData")
                }
            }

            if (response.status.isSuccess()) {
                val responseBody: TattooClientProfileResponse = response.body()
                NetworkResult.Success(responseBody)
            } else {
                NetworkResult.Error(response.body())
            }
        } catch (e: Exception) {
            NetworkResult.Exception(e)
        }
    }
}