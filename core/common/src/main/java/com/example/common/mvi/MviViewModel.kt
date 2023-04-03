package com.example.common.mvi

import kotlinx.coroutines.flow.StateFlow

interface MviViewModel <S: MviState, A: MviAction> {

    val uiState: StateFlow<S>

    fun processAction(action: A) = Unit
}
