package com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.model.entity.Beer
import com.example.meditationcomposeapp.ui.theme.colorText

@Composable
fun BeerItem(beer: Beer) {
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
                    fontSize = 25.sp,
                    color = MaterialTheme.colors.colorText,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = beer.ibu,
                    fontSize = 25.sp,
                    color = MaterialTheme.colors.colorText,
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = beer.brand, modifier = Modifier.fillMaxWidth(),
                fontSize = 18.sp,
                color = MaterialTheme.colors.colorText,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${stringResource(id = R.string.style_is)} ${beer.style}",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 15.sp,
                color = MaterialTheme.colors.colorText,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${stringResource(id = R.string.malts_is)} ${beer.malts}",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 15.sp,
                color = MaterialTheme.colors.colorText
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "${stringResource(id = R.string.blg_is)} ${beer.blg}",
                    modifier = Modifier.weight(1F),
                    fontSize = 15.sp,
                    color = MaterialTheme.colors.colorText
                )
                Text(
                    text = "${stringResource(id = R.string.alcohol_is)} ${beer.alcohol}",
                    modifier = Modifier.weight(1F),
                    fontSize = 15.sp,
                    color = MaterialTheme.colors.colorText
                )
            }
            Spacer(modifier = Modifier.height(18.dp))
            TryNowButton()
        }
    }
}

@Preview
@Composable
fun previewBeerItem() {
    BeerItem(
        Beer(
            brand = "Birra Moretti",
            name = "Sapporo Premium",
            style = "Smoke-flavored",
            malts = "Caramel",
            ibu = "13 IBU",
            alcohol = "4.9%",
            blg = "14.6°Blg"
        )
    )
}