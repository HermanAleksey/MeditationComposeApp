package com.justparokq.core.design_system.dialog

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.justparokq.core.design_system.R

@Composable
fun MeditationDialogFormer(
    onDismissRequest: () -> Unit,
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = false,
    dialogContent: @Composable () -> Unit
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            dismissOnBackPress = dismissOnBackPress,
            dismissOnClickOutside = dismissOnClickOutside
        )
    ) {
        Card(
            modifier = Modifier
                .width(294.dp)
                .heightIn(max = screenHeight.value.times(0.8).dp),
            backgroundColor = MaterialTheme.colors.surface,
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.radius_pop_up_corner)),
            elevation = 6.dp
        ) {
            dialogContent()
        }
    }
}
