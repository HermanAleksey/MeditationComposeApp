package com.justparokq.feature.beer_sorts.internal.presentation.beer_list.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.justparokq.core.database.model.BeerListItem
import com.justparokq.core.design_system.AppTheme
import com.justparokq.feature.beer_sorts.R
import com.justparokq.feature.beer_sorts.internal.common.TryNowButton

@Composable
internal fun BeerItem(
    beer: BeerListItem,
    onClick: () -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 22.dp,
                    vertical = 16.dp
                )
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = beer.name,
                    style = MaterialTheme.typography.h5.copy(
                        color = MaterialTheme.colors.onSurface,
                    ),
                    modifier = Modifier.weight(1f)
                )
                beer.ibu?.let {
                    Text(
                        text = "${it.toInt()} ${stringResource(id = R.string.ibu)}",
                        style = MaterialTheme.typography.h5.copy(
                            color = MaterialTheme.colors.onSurface,
                            fontWeight = FontWeight.W700
                        ),
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = beer.tagline,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h6.copy(
                    color = MaterialTheme.colors.onSurface,
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${stringResource(id = R.string.first_brewed)} ${beer.firstBrewed}",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.onSurface,
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            BeerParamsTable(
                abv = beer.abv,
                ebc = beer.ebc,
                srm = beer.srm,
                ph = beer.ph,
            )

            Spacer(modifier = Modifier.height(10.dp))
            TryNowButton(beer.name, beer.tagline)
        }
    }
}

@Preview
@Composable
private fun BeerItemPreview() {
    AppTheme {
        BeerItem(
            BeerListItem(
                id = 1,
                name = "Sapporo Premium",
                tagline = "Tag Beereaty",
                firstBrewed = "10.05.2000",
                description = "Very Good Beer. Perhaps some dummy text required",
                imageUrl = "https://image.png",
                abv = 10.5,
                ibu = 1.2,
                targetFg = 4343,
                targetOg = 342.0,
                ebc = 12.3,
                srm = 76.0,
                ph = 4.0,
                attenuationLevel = 432.3,
                brewersTips = "brewerTips: 1. drink 2. repeat",
                contributedBy = "by me"
            ),
            onClick = {}
        )
    }
}