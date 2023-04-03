package com.example.common.mvi

import kotlinx.coroutines.flow.StateFlow

interface MviViewModel <S: State> {

    val uiState: StateFlow<S>

    fun processAction(action: Action) = Unit
}
