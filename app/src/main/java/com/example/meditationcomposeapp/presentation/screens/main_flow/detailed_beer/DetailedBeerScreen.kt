package com.example.meditationcomposeapp.presentation.screens.main_flow.detailed_beer

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.model.entity.beer.Beer
import com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list.composable.BeerParamsTable
import com.ramcosta.composedestinations.annotation.Destination
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Destination
@Composable
fun DetailedBeerScreen(
    beer: Beer,
) {
    val paramsWithInfo = remember {
        listOf(
            R.string.abv_is to beer.abv,
            R.string.ebc_is to beer.ebc,
            R.string.srm_is to beer.srm,
            R.string.ph_is to beer.ph,
        ).filter { it.second != null } as List<Pair<Int, Double>>
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(id = R.dimen.padding_horizontal_list))
            .scrollable(rememberScrollState(), Orientation.Vertical),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            BeerDetailsCard {
                GlideImage(
                    imageModel = { beer.imageUrl }, // loading a network image using an URL.
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.FillHeight,
                        alignment = Alignment.Center
                    ),
                    modifier = Modifier
                        .height(200.dp)
                )
            }
        }

        item {
            BeerDetailsCard {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                ) {
                    Text(text = beer.name,
                        style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onSurface,
                            fontSize = 26.sp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = beer.tagline,
                        style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onSurface)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = beer.description,
                        style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onSurface)
                    )
                }
            }
        }

        item {
            BeerDetailsCard {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.beer_params),
                        style = MaterialTheme.typography.body1
                            .copy(color = MaterialTheme.colors.onSurface)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BeerParamsTable(
                        paramsInfo = paramsWithInfo,
                        textStyle = MaterialTheme.typography.body2.copy(
                            fontSize = 18.sp,
                            color = MaterialTheme.colors.onSurface
                        ),
                    )
                }
            }
        }

        item {
            BeerDetailsCard {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.brewers_tips),
                        style = MaterialTheme.typography.body1
                            .copy(color = MaterialTheme.colors.onSurface)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = beer.brewersTips,
                        style = MaterialTheme.typography.body2
                            .copy(color = MaterialTheme.colors.onSurface)
                    )
                }
            }
        }

        item {
            FoodPairingsCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                beer.foodPairing
            )
        }

        item {
            BeerDetailsCard {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                ) {
                    Text(text = stringResource(id = R.string.contributed_by),
                        style = MaterialTheme.typography.body1.copy(
                            color = MaterialTheme.colors.onSurface
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = beer.contributedBy, style = MaterialTheme.typography.body2.copy(
                        color = MaterialTheme.colors.onSurface
                    ))
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun FoodPairingsCard(modifier: Modifier = Modifier, foodPairings: List<String>) {
    BeerDetailsCard {
        Column(modifier = modifier) {
            Text(
                text = stringResource(id = R.string.food_pairings),
                style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.onSurface
                ),
            )
            foodPairings.forEach { foodName ->
                FoodPairingElement(foodName)
            }
        }
    }
}

@Composable
fun FoodPairingElement(foodName: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(5.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colors.onSurface)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = foodName,
            style = MaterialTheme.typography.body2.copy(
                color = MaterialTheme.colors.onSurface
            )
        )
    }
    Spacer(modifier = Modifier.width(8.dp))
}

@Composable
fun BeerDetailsCard(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.radius_list_item_corner)),
        elevation = 0.dp
    ) {
        content()
    }
}