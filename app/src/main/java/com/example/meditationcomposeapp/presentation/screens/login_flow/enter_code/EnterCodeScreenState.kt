package com.example.meditationcomposeapp.presentation.screens.login_flow.enter_code

data class EnterCodeScreenState(
    val isLoading: Boolean = false,
    val code: Array<Int> = arrayOf(
        EMPTY_NUMBER,
        EMPTY_NUMBER,
        EMPTY_NUMBER,
        EMPTY_NUMBER,
        EMPTY_NUMBER
    )
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EnterCodeScreenState

        if (isLoading != other.isLoading) return false
        if (!code.contentEquals(other.code)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = isLoading.hashCode()
        result = 31 * result + code.contentHashCode()
        return result
    }

    companion object {
        const val EMPTY_NUMBER = -1
    }
}