package com.example.beer_sorts.internal.presentation.beer_details

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.beer_sorts.internal.presentation.beer_list.composables.BeerParamsTable
import com.example.design_system.common_composables.ColorBackground
import com.example.feature.beer_sorts.R
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
internal fun InternalDetailedBeerScreen(
    beerId: Int,
    viewModel: DetailedBeerScreenViewModel,
) {
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.onScreenOpened(beerId)
    }

    ColorBackground(
        isLoading = uiState.value.isLoading || uiState.value.beer == null,
        color = MaterialTheme.colors.background
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .scrollable(rememberScrollState(), Orientation.Vertical),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            with(uiState.value.beer) {
                if (this != null) {
                    item {
                        Spacer(modifier = Modifier.height(24.dp))
                    }

                    if (!imageUrl.isNullOrBlank())
                        item {
                            BeerDetailsCard {
                                GlideImage(
                                    imageModel = { imageUrl }, // loading a network image using an URL.
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
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp, horizontal = 16.dp)
                            ) {
                                Text(
                                    text = name,
                                    style = MaterialTheme.typography.body1.copy(
                                        color = MaterialTheme.colors.onSurface,
                                        fontSize = 26.sp
                                    )
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = tagline,
                                    style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onSurface)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = description,
                                    style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onSurface)
                                )
                            }
                        }
                    }

                    item {
                        BeerDetailsCard {
                            Column(
                                modifier = Modifier
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
                                    abv = abv,
                                    ebc = ebc,
                                    srm = srm,
                                    ph = ph,
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
                            Column(
                                modifier = Modifier
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
                                    text = uiState.value.beer!!.brewersTips,
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
                            uiState.value.beer!!.foodPairing
                        )
                    }

                    item {
                        BeerDetailsCard {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp, horizontal = 16.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.contributed_by),
                                    style = MaterialTheme.typography.body1.copy(
                                        color = MaterialTheme.colors.onSurface
                                    )
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = uiState.value.beer!!.contributedBy,
                                    style = MaterialTheme.typography.body2.copy(
                                        color = MaterialTheme.colors.onSurface
                                    )
                                )
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
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
        shape = RoundedCornerShape(20.dp),
        elevation = 0.dp
    ) {
        content()
    }
}