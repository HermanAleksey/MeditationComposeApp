package com.example.common.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class NavigationBaseViewModel<NavRoute> : ViewModel() {

    protected val _navigationEvent: MutableSharedFlow<NavRoute?> =
        MutableSharedFlow(
            replay = 1,
            onBufferOverflow = BufferOverflow.SUSPEND
        )
    val navigationEvent = _navigationEvent.asSharedFlow()

    protected val _isTransactionLaunched = MutableStateFlow(false)
    protected val isTransactionLaunched = _isTransactionLaunched.asStateFlow()

    fun onScreenEntered() {
        _isTransactionLaunched.update {
            false
        }
    }

    protected suspend inline fun navigationEventTransaction(navigationFun: () -> Unit) {
        //if we in the middle of transaction - skip navigation event
        if (isTransactionLaunched.value) return

        //open transaction
        _isTransactionLaunched.update {
            true
        }
        navigationFun.invoke()
        delay(TRANSACTION_DURATION_LIMIT)
        _navigationEvent.emit(null)

        //close transaction, if it wasn't closed by Entered Screen.
        finishTransactionBlock()
    }

    /**
     *  If navigation transaction was interrupted by pressing "BACK" button
     *
     *  In case when we navigate from screen A to screen B, but when navigation
     *  is interrupted in progress (due to animation for example) - Composable
     *  function (Screen A) doesn't invoke it's Launched Effect onStart screen
     * */
    // todo try to use Disposable effect on Screen B to end transaction (requires to make transaction globa obj?)
    protected suspend fun finishTransactionBlock() =
        viewModelScope.launch(Dispatchers.IO) {
            delay(TRANSACTION_DURATION_LIMIT)
            _isTransactionLaunched.update {
                false
            }
        }

    companion object {
        const val TRANSACTION_DURATION_LIMIT: Long = 300
    }
}