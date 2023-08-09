package com.justparokq.feature.main.internal.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Extension
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.justparokq.core.common.utils.UiText
import com.justparokq.core.design_system.AppTheme
import com.justparokq.feature.main.R

internal data class MenuItemModel(
    val title: UiText,
    val icon: ImageVector,
    val backgroundColor: Color,
    val foregroundColor: Color,
    val onClick: () -> Unit = {},
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun MenuItem(modifier: Modifier, model: MenuItemModel) {
    Card(
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.radius_menu_item_corner)),
        modifier = modifier,
        onClick = { model.onClick() }
    ) {
        MenuItemBackground(model.backgroundColor, model.foregroundColor)
        Column {
            Spacer(modifier = Modifier.height(52.dp))
            Row {
                Spacer(modifier = Modifier.width(12.dp))
                Image(
                    imageVector = model.icon,
                    contentDescription = null,
                    alignment = Alignment.BottomCenter,
                    modifier = Modifier
                        .size(24.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Spacer(modifier = Modifier.width(9.dp))
                Text(
                    text = model.title.asString(),
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }

}

@Composable
internal fun MenuItemBackground(backgroundColor: Color, colorForeground: Color) {
    Canvas(
        modifier = Modifier
            .clipToBounds()
            .fillMaxSize()
    ) {
        drawRect(
            color = backgroundColor,
            size = size
        )
        drawCircle(
            color = colorForeground,
            radius = size.height / 2.2f,
            center = Offset(x = size.width / 8.5f, y = size.height / 3 * 1.70f)
        )
        drawCircle(
            color = colorForeground,
            radius = size.height / 3,
            center = Offset(x = size.width / 10 * 4.3f, y = size.height / 3 * 1.75f)
        )
        drawCircle(
            color = colorForeground,
            radius = size.height / 2,
            center = Offset(x = size.width / 10 * 9, y = size.height / 3 * 2.2f)
        )
        drawRect(
            color = colorForeground,
            size = Size(width = size.width, height = size.height / 3),
            topLeft = Offset(x = 0f, y = size.height / 3 * 2)
        )
    }
}

@Preview
@Composable
private fun MenuItemPreview() {
    AppTheme {
        MenuItem(
            modifier = Modifier
                .height(115.dp)
                .width(170.dp),
            MenuItemModel(
                title = UiText.StringResource(R.string.shuffle_puzzle_menu_option),
                icon = Icons.Default.Extension,
                backgroundColor = Color(169, 213, 113),
                foregroundColor = Color(106, 174, 114),
                onClick = { },
            )
        )
    }
}