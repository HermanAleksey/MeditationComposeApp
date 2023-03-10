package com.example.beer_sorts.internal.presentation.beer_list.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feature.beer_sorts.R

@Composable
internal fun BeerParamsTable(
    modifier: Modifier = Modifier,
    abv: Double? = null,
    ebc: Double? = null,
    srm: Double? = null,
    ph: Double? = null,
    textStyle: TextStyle,
) {
    @Suppress("UNCHECKED_CAST")
    val paramsWithInfo = remember {
        listOf(
            R.string.abv_is to abv,
            R.string.ebc_is to ebc,
            R.string.srm_is to srm,
            R.string.ph_is to ph,
        ).filter { it.second != null } as List<Pair<Int, Double>>
    }

    Column(modifier) {
        for (index in paramsWithInfo.indices step 2) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BeerParamLabel(
                    paramName = stringResource(paramsWithInfo[index].first),
                    paramValue = paramsWithInfo[index].second,
                    style = textStyle,
                    modifier = Modifier.weight(1f)
                )
                if (paramsWithInfo.lastIndex != index) {
                    BeerParamLabel(
                        paramName = stringResource(paramsWithInfo[index + 1].first),
                        paramValue = paramsWithInfo[index + 1].second,
                        modifier = Modifier.weight(1f),
                        style = textStyle,
                    )
                }
            }
            if (paramsWithInfo.lastIndex != index && paramsWithInfo.lastIndex + 1 != index)
                Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
internal fun BeerParamLabel(
    paramName: String,
    paramValue: Double,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.body2.copy(
        fontSize = 15.sp
    ),
) {
    Text(
        text = "$paramName $paramValue",
        modifier = modifier,
        style = style,
    )
}