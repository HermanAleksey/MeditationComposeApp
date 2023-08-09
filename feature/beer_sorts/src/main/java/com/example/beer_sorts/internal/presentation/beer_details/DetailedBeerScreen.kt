package com.example.beer_sorts.internal.presentation.beer_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.beer_sorts.api.detailed_beer.DetailedBeerScreenState
import com.example.beer_sorts.internal.presentation.beer_details.composables.BeerDescriptionCard
import com.justparokq.core.model.beer_sorts.Beer
import com.justparokq.core.model.beer_sorts.Ingredients
import com.justparokq.core.model.beer_sorts.MeasurementUnit
import com.example.design_system.AppTheme
import com.example.design_system.common_composables.ColorBackground
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
internal fun InternalDetailedBeerScreen(
    uiState: State<DetailedBeerScreenState>,
    onScreenOpened: () -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        onScreenOpened()
    }

    ColorBackground(
        isLoading = uiState.value.isLoading || uiState.value.beer == null,
        color = MaterialTheme.colors.background,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            uiState.value.beer?.let { beer ->
                if (!beer.imageUrl.isNullOrBlank())
                    GlideImage(
                        imageModel = { beer.imageUrl }, // loading a network image using an URL.
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.FillHeight,
                            alignment = Alignment.Center
                        ),
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                            .padding(vertical = 15.dp)
                    )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    if (!beer.imageUrl.isNullOrBlank())
                        item {
                            Spacer(
                                modifier = Modifier
                                    .height(230.dp)
                            )
                        }

                    item {
                        BeerDescriptionCard(beer = beer)
                    }
                }
            }
        }
    }
}

@Preview(name = "Emtpy beer")
@Composable
fun DetailedBeerScreenPreview() {
    AppTheme {
        InternalDetailedBeerScreen(
            uiState = MutableStateFlow(
                DetailedBeerScreenState()
            ).collectAsState(),
            onScreenOpened = {}
        )
    }
}

@Preview(name = "With beer")
@Composable
fun DetailedBeerScreenPreview2() {
    AppTheme {
        InternalDetailedBeerScreen(
            uiState = MutableStateFlow(
                DetailedBeerScreenState(
                    beer = Beer(
                        id = 1,
                        name = "Sapporo Premium",
                        tagline = "Birra Moretti",
                        firstBrewed = "10.10.2010",
                        description = "Beers can range from having mild flavor to an intense bold taste experience. A mild beer might taste light, delicate, crisp and refreshing. A bolder beer might have stronger flavors that result in a sharper taste which might be described as intense, powerful, robust and hearty",
                        imageUrl = "data:image/webp;base64,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",
                        abv = 10.2,
                        ibu = 13.0,
                        targetFg = 32,
                        targetOg = 29.3,
                        ebc = 23.0,
                        srm = 32.0,
                        ph = 11.2,
                        attenuationLevel = 99.9,
                        volume = null,
                        boilVolume = null,
                        method = null,
                        ingredients = Ingredients(
                            malt = listOf(
                                Ingredients.Malt(
                                    "Maris Otter Extra Pale",
                                    Ingredients.Amount(3.3, MeasurementUnit.KILOGRAMS)
                                ),
                                Ingredients.Malt(
                                    "Caramalt",
                                    Ingredients.Amount(0.2, MeasurementUnit.KILOGRAMS)
                                ),
                                Ingredients.Malt(
                                    "Munich",
                                    Ingredients.Amount(0.4, MeasurementUnit.KILOGRAMS)
                                ),
                            ),
                            hops = listOf(
                                Ingredients.Hops(
                                    "Fuggles",
                                    Ingredients.Amount(25.0, MeasurementUnit.GRAMS),
                                    Ingredients.AddOrder.START,
                                    "bitter"
                                ),
                                Ingredients.Hops(
                                    "First Gold",
                                    Ingredients.Amount(25.0, MeasurementUnit.GRAMS),
                                    Ingredients.AddOrder.START,
                                    "bitter"
                                ),

                                Ingredients.Hops(
                                    "Fuggles",
                                    Ingredients.Amount(37.5, MeasurementUnit.GRAMS),
                                    Ingredients.AddOrder.MIDDLE,
                                    "flavour"
                                ),
                                Ingredients.Hops(
                                    "First Gold",
                                    Ingredients.Amount(37.5, MeasurementUnit.GRAMS),
                                    Ingredients.AddOrder.MIDDLE,
                                    "flavour"
                                ),
                                Ingredients.Hops(
                                    "Cascade",
                                    Ingredients.Amount(37.5, MeasurementUnit.GRAMS),
                                    Ingredients.AddOrder.END,
                                    "flavour"
                                ),
                            ),
                            yeast = "Wyeast 1056 - American Ale™"
                        ),
                        foodPairing = listOf(
                            "Spicy chicken tikka masala",
                            "Grilled chicken quesadilla",
                            "Caramel toffee cake"
                        ),
                        brewersTips = "The earthy and floral aromas from the hops can be overpowering. Drop a little Cascade in at the end of the boil to lift the profile with a bit of citrus.",
                        contributedBy = "Andre Puanijy",
                    )
                )
            ).collectAsState(),
            onScreenOpened = {}
        )
    }
}

