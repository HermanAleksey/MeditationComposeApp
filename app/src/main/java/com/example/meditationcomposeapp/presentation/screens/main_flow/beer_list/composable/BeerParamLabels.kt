package com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BeerParamsTable(paramsInfo: List<Pair<Int, Double>>) {
    for (index in paramsInfo.indices step 2) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BeerParamLabel(
                paramName = stringResource(paramsInfo[index].first),
                paramValue = paramsInfo[index].second,
                modifier = Modifier.weight(1f)
            )
            if (paramsInfo.lastIndex != index) {
                BeerParamLabel(
                    paramName = stringResource(paramsInfo[index + 1].first),
                    paramValue = paramsInfo[index + 1].second,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun BeerParamLabel(paramName: String, paramValue: Double, modifier: Modifier = Modifier) {
    Text(
        text = "$paramName $paramValue",
        modifier = modifier,
        fontSize = 15.sp,
        color = MaterialTheme.colors.onSurface
    )
}