package com.example.authentication.internal.screens.enter

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.authentication.internal.common.LoginMainButton
import com.example.authentication.internal.screens.enter.composable.DontHaveAccountText
import com.example.design_system.AppTheme
import com.example.design_system.common_composables.ImageBackground
import com.example.feature.authentication.R

@Composable
internal fun InternalEnterScreen(
    onDontHaveAccountClick: () -> Unit,
    onEnterClick: () -> Unit,
) {
    val activity = LocalContext.current as? Activity
    BackHandler(enabled = true, onBack = {
        activity?.finish()
    })

    ImageBackground(
        imageRes = R.drawable.background_login,
        testTag = stringResource(id = R.string.enter_screen_test_tag)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .padding(bottom = 18.dp)
                    .fillMaxSize(0.5F),
            ) {
                Image(
                    alignment = Alignment.BottomStart,
                    painter = painterResource(id = com.justparokq.core.design_system.R.drawable.ic_app_icon),
                    contentScale = ContentScale.Fit,
                    contentDescription = null,
                )
            }
            Text(
                text = stringResource(id = R.string.welcome),
                style = MaterialTheme.typography.h3,
            )
            Text(
                text = stringResource(id = R.string.welcome_text),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(120.dp))
            LoginMainButton(
                text = stringResource(id = R.string.login_button),
                modifier = Modifier
                    .fillMaxWidth(0.8F)
                    .wrapContentHeight()
            ) {
                onEnterClick()
            }
            Spacer(modifier = Modifier.height(18.dp))
            DontHaveAccountText(
                onClick = {
                    onDontHaveAccountClick()
                }
            )
        }
    }
}

@Preview
@Composable
fun InternalEnterScreenPreview() {
    AppTheme {
        InternalEnterScreen({}, {})
    }
}
