package com.example.meditationcomposeapp.presentation.ui_controls.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.model.entity.login_flow.UpdateDescriptionModel
import com.example.meditationcomposeapp.presentation.dialogs.types.UpdateDescriptionDialog

sealed class DialogType {

    object EmptyDialog : DialogType()

    class UpdateDescriptionDialog(val updatesLog: List<UpdateDescriptionModel>) : DialogType()

}

@Composable
fun MeditationDialog(
    onDismissRequest: () -> Unit,
    dialogType: DialogType
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .heightIn(max = screenHeight.value.times(0.8).dp),
            backgroundColor = MaterialTheme.colors.surface,
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.radius_pop_up_corner)),
            elevation = 6.dp
        ) {
            when (dialogType) {
                is DialogType.EmptyDialog -> {
                    Box(modifier = Modifier.fillMaxSize(0.1f))
                }
                is DialogType.UpdateDescriptionDialog -> {
                    UpdateDescriptionDialog(updatesLog = dialogType.updatesLog)
                }
            }
        }
    }
}