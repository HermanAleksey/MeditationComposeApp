package com.example.authentication.internal.screens.enter.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.design_system.AppTheme
import com.example.feature.authentication.R

@Composable
internal fun DontHaveAccountText(onClick: () -> Unit) {
    Text(
        text = stringResource(id = R.string.dont_have_account) +
                " " + stringResource(id = R.string.sign_up),
        style = MaterialTheme.typography.h6.copy(
            lineHeight = 20.sp
        ),
        modifier = Modifier.clickable {
            onClick()
        }
    )
}

@Preview
@Composable
private fun DontHaveAccountTextPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
        ) {
            DontHaveAccountText {}
        }
    }
}