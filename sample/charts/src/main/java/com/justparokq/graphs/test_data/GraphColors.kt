package com.justparokq.graphs.test_data

import androidx.compose.ui.graphics.Color

object GraphColors {
    var colors = listOf(
        Color(0XFFF44336),
        Color(0XFFE91E63),
        Color(0XFF9C27B0),
        Color(0XFF673AB7),
        Color(0XFF3F51B5),
        Color(0XFF03A9F4),
        Color(0XFF009688),
        Color(0XFFCDDC39),
        Color(0XFFFFC107),
        Color(0XFFFF5722),
        Color(0XFF795548),
        Color(0XFF9E9E9E),
        Color(0XFF607D8B)
    )

    fun getRandomColor(): Color {
        val randomIndex = (Math.random() * 100).toInt().mod(colors.lastIndex)
        return colors[randomIndex]
    }
}
