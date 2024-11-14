package br.com.connectattoo.domain.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

sealed class NetworkResult<T : Any> {
    @Serializable
    class Success<T : Any>(val data: T) : NetworkResult<T>()

    @Serializable
    class Error<T : Any>(val errorResponse: ErrorResponse?) : NetworkResult<T>()
    class Exception<T : Any>(val e: Throwable) : NetworkResult<T>()
}

@Serializable
data class ErrorResponse(
    val status: Int,
    val message: String
)

/**
 *   CallBack to handle with Success
 */
suspend fun <T : Any> NetworkResult<T>.onSuccess(
    executable: suspend (T) -> Unit
): NetworkResult<T> = apply {
    if (this is NetworkResult.Success<T>) {
        executable(data)
    }
}

/**
 *   CallBack to handle with Error
 */
suspend fun <T : Any> NetworkResult<T>.onError(
    executable: suspend (body: String?) -> Unit
): NetworkResult<T> = apply {
    if (this is NetworkResult.Error<T>) {
        val errorResponse: ErrorResponse =
            Json.decodeFromString("${errorResponse?.status} -> ${errorResponse?.message}")
        executable(errorResponse.message)
    }
}

/**
 *   CallBack to handle with Exception
 */
suspend fun <T : Any> NetworkResult<T>.onException(
    executable: suspend (e: Throwable) -> Unit
): NetworkResult<T> = apply {
    if (this is NetworkResult.Exception<T>) {
        executable(e)
    }
}