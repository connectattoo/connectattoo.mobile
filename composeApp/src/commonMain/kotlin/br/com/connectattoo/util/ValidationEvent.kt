package br.com.connectattoo.util

sealed class ValidationEvent {
    data object Success : ValidationEvent()
    data object Failed : ValidationEvent()
}