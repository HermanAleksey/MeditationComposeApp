package com.example.authentication.internal.screens.enter_code

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.authentication.api.enter_code_screen.EnterCodeScreenNavDependencies
import com.example.authentication.api.enter_code_screen.EnterCodeScreenViewModel
import com.example.authentication.internal.screens.enter_code.composable.CodePanel
import com.example.authentication.internal.screens.login.composable.LoginFlowBackground
import com.example.common.navigation.NavDependenciesProvider
import com.example.feature.authentication.R

@Composable
internal fun InternalEnterCodeScreen(
    login: String,
    viewModel: EnterCodeScreenViewModel,
) {
    val uiState = viewModel.uiState.collectAsState()

    val navDependencies = ((LocalContext.current as? Activity) as NavDependenciesProvider)
        .provideDependencies(EnterCodeScreenNavDependencies::class.java)

    LaunchedEffect(key1 = viewModel.navigationEvent.collectAsState()) {
        viewModel.navigationEvent.collect { event ->
            event?.tryNavigate(navDependencies)
        }
    }


    /**
     * Use to transmit navigation method and
     * also serve as callback for event when
     * last digit on CodePanel was filled
     * */
    fun onLastDigitFilled() {
        viewModel.onLastDigitFilled(
            login
        )
    }

    LoginFlowBackground(
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
                painter = painterResource(id = R.drawable.ic_logo_white),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .padding(top = 100.dp)
                    .height(40.dp)
            )
            Text(
                text = stringResource(id = R.string.password_recovery),
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(top = 31.dp)
            )
            Text(
                text = stringResource(id = R.string.password_recovery_desc),
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .alpha(0.7F)
            )
            CodePanel(
                isEnabled = !uiState.value.isLoading,
                code = uiState.value.code,
                onCodeDigitChanged = { position, number ->
                    viewModel.onCodeDigitChanged(
                        position,
                        number
                    )
                },
                onLastDigitFilled = ::onLastDigitFilled
            )
            Spacer(modifier = Modifier.padding(top = 80.dp))
        }
    }
}