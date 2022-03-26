package com.example.meditationcomposeapp.view.enter.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.meditationcomposeapp.ui.theme.Alegreya

@OptIn(ExperimentalUnitApi::class)
@Composable
fun LoginMainButton(
    text: String,
    modifier: Modifier,
    onClick: ()->Unit
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
        shape = RoundedCornerShape(10.dp),
        modifier = modifier

    ) {
        Text(
            text = text,
            fontSize = TextUnit(25F, TextUnitType.Sp),
            color = Color.White,
            textAlign = TextAlign.Center,
            fontFamily = Alegreya,
            fontWeight = FontWeight.W500,
            modifier = Modifier
                .padding(vertical = 4.dp)
        )
    }
}