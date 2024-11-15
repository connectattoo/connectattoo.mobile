package br.com.connectattoo.data.repository

import br.com.connectattoo.domain.model.ArtistData
import br.com.connectattoo.domain.model.ClientData
import br.com.connectattoo.domain.model.TokenData
import br.com.connectattoo.domain.network.ErrorResponse
import br.com.connectattoo.domain.network.NetworkResult
import br.com.connectattoo.domain.repository.AuthRepository
import br.com.connectattoo.util.PreferencesHelper
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.isSuccess

class AuthRepositoryImpl(
    private val client: HttpClient,
    private val baseURL: String,
    private val preferencesHelper: PreferencesHelper
) : AuthRepository {

    override suspend fun registerClient(clientData: ClientData): NetworkResult<TokenData> {
        return try {
            val response = client.post("$baseURL/auth/register") {
                contentType(ContentType.Application.Json)
                setBody(clientData)
            }

            if (response.status.isSuccess()) {
                val tokenData: TokenData = response.body()

                if (!tokenData.accessToken.isNullOrEmpty()) {
                    preferencesHelper.saveToken(tokenData.accessToken)
                }
                NetworkResult.Success(tokenData)
            } else {
                NetworkResult.Error(response.body())
            }
        } catch (e: Exception) {
            NetworkResult.Exception(e)
        }
    }

    override suspend fun confirmEmail(token: String): NetworkResult<TokenData> {
        return try {
            val response: HttpResponse = client.get("$baseURL/users/confirmation") {
                headers {
                    append(HttpHeaders.Authorization, "Bearer $token")
                }
            }

            if (response.status.isSuccess()) {
                val responseBody: TokenData = response.body()
                NetworkResult.Success(responseBody)
            } else {
                NetworkResult.Error(response.body())
            }
        } catch (e: Exception) {
            NetworkResult.Exception(e)
        }
    }

    override suspend fun registerTattooArtist(artistData: ArtistData): NetworkResult<TokenData> {
        return try {
            val response = client.post("$baseURL/auth/register/artist") {
                contentType(ContentType.Application.Json)
                setBody(artistData)
            }

            if (response.status.isSuccess()) {
                val tokenData: TokenData = response.body()

                if (!tokenData.accessToken.isNullOrEmpty()) {
                    preferencesHelper.saveToken(tokenData.accessToken)
                }
                NetworkResult.Success(tokenData)
            } else {
                val errorBody = response.bodyAsText()
                if (errorBody.contains("message")) {
                    val errorMessage = "Erro(s) no cadastro: $errorBody"
                    NetworkResult.Error(ErrorResponse(response.status.value, errorMessage))
                } else {
                    NetworkResult.Error(ErrorResponse(response.status.value, errorBody))
                }
            }
        } catch (e: Exception) {
            NetworkResult.Exception(e)
        }
    }

}