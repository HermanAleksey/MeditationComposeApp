package com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.ui.theme.Alegreya

data class MenuItemModel(
    val title: String,
    val painterRes: Int,
    val backgroundColor: Color,
    val foregroundColor: Color,
    val onClick: () -> Unit = {},
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuItem(modifier: Modifier, model: MenuItemModel) {
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
                    painter = painterResource(id = model.painterRes),
                    contentDescription = "",
                    alignment = Alignment.BottomCenter,
                    modifier = Modifier
                        .size(18.dp)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Spacer(modifier = Modifier.width(9.dp))
                Text(
                    text = model.title,
                    fontSize = 20.sp,
                    color = Color.White,
                    fontFamily = Alegreya,
                    fontWeight = FontWeight.W500
                )
            }
        }
    }

}

@Composable
fun MenuItemBackground(backgroundColor: Color, colorForeground: Color) {
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