package br.com.connectattoo.data.repository

import br.com.connectattoo.domain.base.DataResult
import br.com.connectattoo.domain.model.ArtistData
import br.com.connectattoo.domain.model.ClientData
import br.com.connectattoo.domain.model.TokenData
import br.com.connectattoo.domain.repository.AuthRepository
import br.com.connectattoo.util.PreferencesHelper
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthRepositoryImpl(
    private val client: HttpClient,
    private val baseURL: String,
    private val preferencesHelper: PreferencesHelper
) : AuthRepository {

    override suspend fun registerClient(clientData: ClientData): DataResult<TokenData> {
        return try {
            val response: TokenData = client.post("$baseURL/auth/register") {
                contentType(ContentType.Application.Json)
                setBody(clientData)
            }.body()
            if (response.accessToken.isNotEmpty()) {
                preferencesHelper.saveToken(response.accessToken)
            }
            DataResult.Success(response)
        } catch (e: Exception) {
            println(e.message)
            DataResult.Failure(e)
        }
    }

    override suspend fun registerTattooArtist(artistData: ArtistData): DataResult<TokenData> {
        return try {
            val response: TokenData = client.post("$baseURL/auth/register/artist") {
                contentType(ContentType.Application.Json)
                setBody(artistData)
            }.body()
            if (response.accessToken.isNotEmpty()) {
                preferencesHelper.saveToken(response.accessToken)
            }

            DataResult.Success(response)
        } catch (e: Exception) {
            println(e.message)
            DataResult.Failure(e)
        }
    }

}