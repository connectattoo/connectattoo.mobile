package br.com.connectattoo

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class Greeting() {
    private val platform = getPlatform()

    private val client = HttpClient()

    suspend fun greeting(): String {
        val response = client.get("https://ktor.io/docs/")
        println(response.status)
        return response.bodyAsText()
    }

    fun greet(): String {
        var resp = ""
        CoroutineScope(Dispatchers.IO).launch {
            resp = greeting()

        }
        return "Hello, ${resp}!"
    }
}