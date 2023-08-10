package com.justparokq.feature.beer_sorts.internal.presentation.beer_list.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.justparokq.core.design_system.AppTheme
import com.justparokq.feature.beer_sorts.R

@Composable
internal fun BeerParamsTable(
    abv: Double? = null,
    ebc: Double? = null,
    srm: Double? = null,
    ph: Double? = null,
) {
    val paramsWithInfo = remember {
        listOf(
            R.string.abv_is to abv,
            R.string.ebc_is to ebc,
            R.string.srm_is to srm,
            R.string.ph_is to ph,
        ).filter { it.second != null }
            .map { (stringId, value) ->
                Pair(stringId, value ?: 0.0)
            }
    }
    val textStyle = MaterialTheme.typography.body1.copy(
        color = MaterialTheme.colors.onSurface
    )

    Column {
        for (index in paramsWithInfo.indices step 2) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(paramsWithInfo[index].first) +
                            " " + paramsWithInfo[index].second,
                    modifier = Modifier.weight(1f),
                    style = textStyle,
                )
                if (paramsWithInfo.lastIndex != index) {
                    Text(
                        text = stringResource(paramsWithInfo[index + 1].first) +
                                " " + paramsWithInfo[index + 1].second,
                        modifier = Modifier.weight(1f),
                        style = textStyle,
                    )
                }
            }

            if (paramsWithInfo.lastIndex != index
                && paramsWithInfo.lastIndex + 1 != index
            )
                Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview
@Composable
private fun BeerParamsTablePreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
        ) {
            BeerParamsTable(
                abv = 10.0,
                ebc = 23.3,
                srm = 234.3,
                ph = 2.1
            )
        }
    }
}