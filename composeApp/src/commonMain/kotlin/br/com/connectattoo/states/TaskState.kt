package br.com.connectattoo.states

sealed class TaskState {
    data object Idle: TaskState()
    data object Loading: TaskState()
}