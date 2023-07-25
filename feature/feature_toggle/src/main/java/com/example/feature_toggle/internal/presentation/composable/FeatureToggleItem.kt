package com.example.feature_toggle.internal.presentation.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.AppTheme
import com.example.feature_toggle.internal.entity.FeatureToggleUiItem

@Composable
fun FeatureToggleItem(
    item: FeatureToggleUiItem,
    onClickItem: (isSelected: Boolean) -> Unit,
    onItemLongClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isChecked by remember {
        mutableStateOf(item.isChecked)
    }
    Card(
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        onItemLongClick()
                    }
                )
            },
        shape = RoundedCornerShape(20.dp),
        backgroundColor = MaterialTheme.colors.background,
        border = BorderStroke(2.dp, MaterialTheme.colors.onBackground)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            Arrangement.SpaceBetween,
            Alignment.CenterVertically,
        ) {
            Text(
                text = item.title, style = MaterialTheme.typography.h5.copy(
                    color = MaterialTheme.colors.onBackground
                )
            )
            Switch(
                checked = isChecked,
                onCheckedChange = {
                    isChecked = !isChecked
                    onClickItem(isChecked)
                },
                //todo add colors according to theme
                colors = SwitchDefaults.colors()
            )
        }
    }

}

@Preview(name = "Switch turned on")
@Composable
fun FeatureToggleItemPreviewOn() {
    AppTheme {
        FeatureToggleItem(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .height(70.dp),
            item = FeatureToggleUiItem(
                isChecked = true,
                title = "Auth web flow",
                description = "turn on..."
            ),
            onClickItem = {},
            onItemLongClick = {}
        )
    }
}