package com.example.beer_sorts.internal.presentation.beer_details.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.beer_sorts.internal.presentation.beer_list.composables.TryNowButton
import com.example.core.model.beer_sorts.Beer
import com.example.core.model.beer_sorts.Ingredients
import com.example.core.model.beer_sorts.MeasurementUnit
import com.example.design_system.AppTheme
import com.example.feature.beer_sorts.R
import kotlin.math.roundToInt

const val HORIZONTAL_PADDING = 22

@Composable
internal fun BeerDescriptionCard(beer: Beer) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(22.dp))
            DescriptionHeader(beer)

            Spacer(modifier = Modifier.height(30.dp))
            ParametersRow(beer)

            Spacer(modifier = Modifier.height(30.dp))
            AuthorsDescription(beer.description)

            Spacer(modifier = Modifier.height(20.dp))
            BrewersTips(beer.brewersTips)

            if (beer.foodPairing.isNotEmpty()) {
                Spacer(modifier = Modifier.height(20.dp))
                FoodPairingsList(beer.foodPairing)
            }

            beer.ingredients?.let { ingredients ->
                Spacer(modifier = Modifier.height(20.dp))
                IngredientsDescriptionList(ingredients)
            }

            Spacer(modifier = Modifier.height(20.dp))
            ContributedByLabel(beer.contributedBy)

            //todo move button to higher levels so its floating.
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                TryNowButton(beerName = beer.name, tagline = beer.tagline)
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
private fun DescriptionHeader(
    beer: Beer,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = HORIZONTAL_PADDING.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = beer.name, style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.onSurface
                )
            )
            Text(
                text = beer.tagline, style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.onSurface
                )
            )
        }
        beer.ibu?.let { ibu ->
            Text(
                text = "${ibu.roundToInt()} ${stringResource(id = R.string.ibu)}",
                style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.onSurface
                )
            )
        }
    }
}

@Composable
private fun ParametersRow(beer: Beer) {
    val paramsWithInfo: List<Pair<Int, String>> = remember {
        listOf(
            R.string.abv to beer.abv,
            R.string.ebc to beer.ebc,
            R.string.srm to beer.srm,
            R.string.ph to beer.ph,
            R.string.og to beer.targetOg,
            R.string.fg to beer.targetFg?.toDouble(),
            R.string.attenuation_level to beer.attenuationLevel,
        ).filter { it.second != null }
            .map { pair ->
                Pair(
                    pair.first,
                    pair.second?.roundToInt().toString()
                )
            }
    }
    val paramsListLastIndex = remember {
        paramsWithInfo.lastIndex
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        itemsIndexed(
            items = paramsWithInfo.toTypedArray(),
            key = null,
        ) { index, pair ->
            Row(verticalAlignment = Alignment.CenterVertically) {

                Spacer(modifier = Modifier.width(15.dp))
                ParameterItem(
                    title = stringResource(id = pair.first),
                    value = pair.second
                )
                Spacer(modifier = Modifier.width(15.dp))
                if (index < paramsListLastIndex)
                    Box(
                        modifier = Modifier
                            .height(32.dp)
                            .width(1.dp)
                            .background(Color.Gray)
                    )
            }
        }
    }
}

@Composable
private fun ParameterItem(title: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.onSurface,
                fontSize = TextUnit(15.0f, TextUnitType.Sp),
                fontWeight = FontWeight.SemiBold
            )
        )
        Text(
            text = value,
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.onSurface,
                fontSize = TextUnit(16.0f, TextUnitType.Sp),
            )
        )
    }
}

@Composable
private fun AuthorsDescription(beerDescription: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = HORIZONTAL_PADDING.dp),
    ) {
        Text(
            text = stringResource(id = R.string.description_title),
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.onSurface,
                fontSize = TextUnit(18.0f, TextUnitType.Sp),
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "${stringResource(id = R.string.paragraph)}$beerDescription",
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.onSurface,
                fontSize = TextUnit(16.0f, TextUnitType.Sp),
            )
        )
    }
}

@Composable
private fun BrewersTips(tips: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = HORIZONTAL_PADDING.dp),
    ) {
        Text(
            text = stringResource(id = R.string.brewers_tips),
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.onSurface,
                fontSize = TextUnit(18.0f, TextUnitType.Sp),
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "${stringResource(id = R.string.paragraph)}$tips",
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.onSurface,
                fontSize = TextUnit(16.0f, TextUnitType.Sp),
            )
        )
    }
}

@Composable
private fun FoodPairingsList(foodPairings: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = HORIZONTAL_PADDING.dp),
    ) {
        Text(
            text = stringResource(id = R.string.food_pairings),
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.onSurface,
                fontSize = TextUnit(18.0f, TextUnitType.Sp),
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = stringResource(id = R.string.paragraph) + foodPairings
                .drop(1)
                .fold(foodPairings[0]) { acc: String, s: String ->
                    "$acc, $s"
                },
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.onSurface,
                fontSize = TextUnit(16.0f, TextUnitType.Sp),
            )
        )
    }
}

@Composable
private fun MeasurementUnit.toAbbreviation(): String {
    val index = MeasurementUnit.values().indexOf(this)
    return stringArrayResource(id = R.array.measurement_units)[index]
}

@Composable
private fun Ingredients.AddOrder.toFormatString(): String {
    val index = Ingredients.AddOrder.values().indexOf(this)
    return stringArrayResource(id = R.array.add_order_values)[index]
}

@Composable
private fun IngredientsDescriptionList(ingredients: Ingredients) {
    val malts = StringBuilder()
        .append(stringResource(id = R.string.paragraph))
        .append(stringResource(id = R.string.malts_is) + " ")
        .apply {
            ingredients.malt.forEachIndexed { index, malt ->
                append(
                    stringResource(
                        id = R.string.malt_information,
                        formatArgs = arrayOf(
                            malt.name,
                            malt.amount.value.toFloat(),
                            malt.amount.unit.toAbbreviation()
                        )
                    )
                )
                if (index != ingredients.malt.lastIndex)
                    append(", ")
            }
        }
    val hops = StringBuilder()
        .append(stringResource(id = R.string.paragraph))
        .append(stringResource(id = R.string.hops_is) + " ")
        .apply {
            ingredients.hops.forEachIndexed { index, hop ->
                append(
                    if (hop.add.toFormatString().isBlank())
                        stringResource(
                            id = R.string.hops_information,
                            formatArgs = arrayOf(
                                hop.name,
                                hop.amount.value.toFloat(),
                                hop.amount.unit.toAbbreviation()
                            )
                        )
                    else stringResource(
                        id = R.string.hops_information_with_order,
                        formatArgs = arrayOf(
                            hop.name,
                            hop.amount.value.toFloat(),
                            hop.amount.unit.toAbbreviation(),
                            hop.add.toFormatString()
                        )
                    )
                )
                if (index != ingredients.hops.lastIndex)
                    append(", ")
            }
        }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = HORIZONTAL_PADDING.dp),
    ) {
        Text(
            text = stringResource(id = R.string.ingredients),
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.onSurface,
                fontSize = TextUnit(18.0f, TextUnitType.Sp),
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = malts.toString(),
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.onSurface,
                fontSize = TextUnit(16.0f, TextUnitType.Sp),
            )
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = hops.toString(),
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.onSurface,
                fontSize = TextUnit(16.0f, TextUnitType.Sp),
            )
        )
    }
}

@Composable
private fun ContributedByLabel(name: String) {
    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = HORIZONTAL_PADDING.dp),
    ) {
        Text(
            text = stringResource(id = R.string.contributed_by),
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.onSurface,
                fontSize = TextUnit(14.0f, TextUnitType.Sp),
            )
        )
        Text(
            text = name,
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.onSurface,
                fontSize = TextUnit(16.0f, TextUnitType.Sp),
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}


@Preview
@Composable
internal fun DescriptionHeaderPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            DescriptionHeader(
                beer = getBeerForPreview(),
            )
        }
    }
}

@Preview
@Composable
internal fun ParametersRowPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            ParametersRow(
                beer = getBeerForPreview(),
            )
        }
    }
}

@Preview
@Composable
internal fun AuthorsDescriptionPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            AuthorsDescription(
                beerDescription = getBeerForPreview().description,
            )
        }
    }
}

@Preview
@Composable
internal fun BrewersTipsPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            BrewersTips(
                tips = getBeerForPreview().brewersTips,
            )
        }
    }
}

@Preview
@Composable
internal fun FoodPairingsListPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            FoodPairingsList(
                foodPairings = getBeerForPreview().foodPairing,
            )
        }
    }
}

@Preview
@Composable
internal fun IngredientsDescriptionListPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            IngredientsDescriptionList(
                ingredients = getBeerForPreview().ingredients!!,
            )
        }
    }
}

@Preview
@Composable
internal fun ContributedByLabelPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            ContributedByLabel(
                name = getBeerForPreview().name,
            )
        }
    }
}

@Preview
@Composable
internal fun BeerDescriptionCardPreview() {
    AppTheme {
        LazyColumn {
            item {
                BeerDescriptionCard(
                    beer = getBeerForPreview()
                )
            }
        }
    }
}

private fun getBeerForPreview() = Beer(
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
