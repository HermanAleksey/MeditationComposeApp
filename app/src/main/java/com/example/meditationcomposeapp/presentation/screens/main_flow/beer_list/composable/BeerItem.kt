package com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.model.entity.beer.*
import com.example.meditationcomposeapp.model.entity.beer.Unit

@Composable
fun BeerItem(beer: Beer) {
    val paramsWithInfo = remember {
        listOf(
            R.string.abv_is to beer.abv,
            R.string.ebc_is to beer.ebc,
            R.string.srm_is to beer.srm,
            R.string.ph_is to beer.ph,
        ).filter { it.second != null } as List<Pair<Int, Double>>
    }

    Card(
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.radius_list_item_corner)),
        modifier = Modifier
            .fillMaxWidth(),
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

            BeerParamsTable(paramsInfo = paramsWithInfo)

            Spacer(modifier = Modifier.height(10.dp))
            TryNowButton(beer.name, beer.tagline)
        }
    }
}

@Preview
@Composable
fun previewBeerItem() {
    BeerItem(
        Beer(
            id = 1,
            name = "Name",
            tagline = "Tag line of beer, Beereaty",
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
            volume = Volume(213, Unit.LITRES),
            boilVolume = BoilVolume(23, Unit.LITRES),
            method = Method(
                listOf(),
                Method.Fermentation(Method.Temp(33, Unit.CELSIUS)),
                twist = "twist"
            ),
            ingredients = Ingredients(listOf(), listOf(), null),
            foodPairing = listOf(),
            brewersTips = "brewerTips: 1. drink 2. repeat",
            contributedBy = "by me"
        )
    )
}