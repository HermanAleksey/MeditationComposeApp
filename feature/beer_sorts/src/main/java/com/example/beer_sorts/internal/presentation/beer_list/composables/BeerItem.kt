package com.example.beer_sorts.internal.presentation.beer_list.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.data_source.entity.db.BeerListItem

@Composable
fun BeerItem(
    beer: BeerListItem,
    onClick: () -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.radius_list_item_corner)),
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
                    horizontal = dimensionResource(id = R.dimen.padding_horizontal_list_item),
                    vertical = dimensionResource(id = R.dimen.padding_vertical_list_item)
                )
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = beer.name,
                    style = MaterialTheme.typography.body1.copy(
                        color = MaterialTheme.colors.onSurface
                    ),
                    modifier = Modifier.weight(1f)
                )
                beer.ibu?.let {
                    Text(
                        text = "${it.toInt()} ${stringResource(id = R.string.ibu)}",
                        style = MaterialTheme.typography.body1.copy(
                            color = MaterialTheme.colors.onSurface,
                            fontWeight = FontWeight.W700
                        ),
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = beer.tagline, modifier = Modifier.fillMaxWidth(),
                fontSize = 18.sp,
                color = MaterialTheme.colors.onSurface,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${stringResource(id = R.string.first_brewed)} ${beer.firstBrewed}",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 15.sp,
                color = MaterialTheme.colors.onSurface,
            )
            Spacer(modifier = Modifier.height(8.dp))

            BeerParamsTable(
                abv = beer.abv,
                ebc = beer.ebc,
                srm = beer.srm,
                ph = beer.ph,
                textStyle = MaterialTheme.typography.body2.copy(
                    fontSize = 15.sp, color = MaterialTheme.colors.onSurface
                )
            )

            Spacer(modifier = Modifier.height(10.dp))
            TryNowButton(beer.name, beer.tagline)
        }
    }
}

//@Preview
//@Composable
//fun previewBeerItem() {
//    BeerItem(
//        BeerDB(
//            id = 1,
//            name = "Name",
//            tagline = "Tag line of beer, Beereaty",
//            firstBrewed = "10.05.2000",
//            description = "Very Good Beer. Perhaps some dummy text required",
//            imageUrl = "https://image.png",
//            abv = 10.5,
//            ibu = 1.2,
//            targetFg = 4343,
//            targetOg = 342.0,
//            ebc = 12.3,
//            srm = 76.0,
//            ph = 4.0,
//            attenuationLevel = 432.3,
//            brewersTips = "brewerTips: 1. drink 2. repeat",
//            contributedBy = "by me"
//        )
//    )
//}