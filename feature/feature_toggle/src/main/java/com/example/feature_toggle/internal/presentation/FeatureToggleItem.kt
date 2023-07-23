package com.example.feature_toggle.internal.presentation

import android.icu.util.Calendar
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feature_toggle.internal.model.FeatureToggleUiItem

@Composable
internal fun FeatureToggleItem(
    item: FeatureToggleUiItem,
    onClickItem: (FeatureToggleUiItem) -> Unit,
    onItemPressAndHold: (FeatureToggleUiItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        val tapStartTime: Long = Calendar.getInstance().timeInMillis
                        val tapEndTime: Long
                        try {
                            awaitRelease()
                        } finally {
                            tapEndTime = Calendar.getInstance().timeInMillis
                            val tapDuration = tapEndTime - tapStartTime
                            //todo remove magic
                            if (tapDuration > 1000)
                                onItemPressAndHold(item)
                        }
                    },
                )
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            Arrangement.SpaceBetween,
            Alignment.CenterVertically,
        ) {
            Text(text = item.title, style = MaterialTheme.typography.h5)
            Switch(
                checked = item.isChecked,
                onCheckedChange = { onClickItem(item) }
            )
        }
    }

}

@Preview(name = "Switch turned on")
@Composable
fun FeatureToggleItemPreviewOn() {
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
        onItemPressAndHold = {}
    )
}