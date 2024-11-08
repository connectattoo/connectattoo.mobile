package br.com.connectattoo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform