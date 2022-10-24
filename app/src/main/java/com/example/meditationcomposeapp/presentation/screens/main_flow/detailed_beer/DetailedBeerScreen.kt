package com.example.meditationcomposeapp.presentation.screens.main_flow.detailed_beer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.model.entity.beer.Beer
import com.ramcosta.composedestinations.annotation.Destination
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Destination
@Composable
fun DetailedBeerScreen(
    beer: Beer,
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(dimensionResource(id = R.dimen.padding_horizontal_list))) {

        BeerDetailsCard {
            GlideImage(
                imageModel = { beer.imageUrl }, // loading a network image using an URL.
                imageOptions = ImageOptions(
                    contentScale = ContentScale.FillHeight,
                    alignment = Alignment.Center
                ),
                modifier = Modifier.height(200.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        BeerDetailsCard() {
            Column() {
                Text(text = beer.name, style = MaterialTheme.typography.body1)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = beer.tagline, style = MaterialTheme.typography.body1)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = beer.description, style = MaterialTheme.typography.body2)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        BeerDetailsCard() {
            Text(text = beer.name, style = MaterialTheme.typography.body1)
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun BeerDetailsCard(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.radius_list_item_corner)),
        elevation = 0.dp
    ) {
        content()
    }
}