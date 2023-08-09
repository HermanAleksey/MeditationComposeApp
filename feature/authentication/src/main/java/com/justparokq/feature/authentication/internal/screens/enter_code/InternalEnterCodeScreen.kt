package com.justparokq.feature.authentication.internal.screens.enter_code

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.justparokq.core.design_system.AppTheme
import com.justparokq.core.design_system.common_composables.DefaultAppBackground
import com.justparokq.feature.authentication.R
import com.justparokq.feature.authentication.api.enter_code_screen.EnterCodeAction
import com.justparokq.feature.authentication.api.enter_code_screen.EnterCodeScreenState
import com.justparokq.feature.authentication.internal.screens.enter_code.composable.CodePanel
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
internal fun InternalEnterCodeScreen(
    login: String,
    uiState: State<EnterCodeScreenState>,
    processAction: (EnterCodeAction) -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        processAction(EnterCodeAction.FirstLaunch(login))
    }

    DefaultAppBackground(
        isLoading = uiState.value.isLoading
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 35.dp, end = 34.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = com.justparokq.core.design_system.R.drawable.ic_app_icon),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .padding(top = 100.dp)
                    .height(40.dp)
            )
            Text(
                text = stringResource(id = R.string.password_recovery),
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(top = 31.dp)
            )
            Text(
                text = stringResource(id = R.string.password_recovery_desc),
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .alpha(0.7F)
            )
            CodePanel(
                isEnabled = !uiState.value.isLoading,
                isCodeFullyInputted = uiState.value.isCodeFullyInputted,
                code = uiState.value.code,
                onCodeDigitChanged = { position, number ->
                    processAction(EnterCodeAction.CodeDigitChanged(position, number))
                },
                onLastDigitFilled = fun() {
                    /**
                     * Use to transmit navigation method and
                     * also serve as callback for event when
                     * last digit on CodePanel was filled
                     * */
                    /**
                     * Use to transmit navigation method and
                     * also serve as callback for event when
                     * last digit on CodePanel was filled
                     * */
                    processAction(EnterCodeAction.LastDigitFilled)
                })
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Preview
@Composable
fun InternalEnterCodeScreenPreview() {
    AppTheme {
        InternalEnterCodeScreen(
            uiState = MutableStateFlow(EnterCodeScreenState()).collectAsState(),
            processAction = {},
            login = "login"
        )
    }
}