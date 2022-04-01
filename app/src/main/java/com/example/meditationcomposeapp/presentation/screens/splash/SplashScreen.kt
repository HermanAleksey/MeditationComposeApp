package com.example.meditationcomposeapp.presentation.screens.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.presentation.navigation.Screen
import com.example.meditationcomposeapp.ui.theme.Purple700
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(/*navController: NavHostController*/) {
//    var startAnimation by remember { mutableStateOf(false) }
//    val alphaAnim = animateFloatAsState(
//        targetValue = if (startAnimation) 1f else 0f,
//        animationSpec = tween(
//            durationMillis = 3000
//        )
//    )
//
//    LaunchedEffect(key1 = true) {
//        startAnimation = true
//        delay(4000)
//        navController.popBackStack()
//        navController.navigate(Screen.Enter.route)
//    }
//    Splash(alpha = alphaAnim.value)
}

//@Composable
//fun Splash(alpha: Float) {
//    Box(
//        modifier = Modifier
////            .background(if (isSystemInDarkTheme()) Color.Black else Purple700)
//            .fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Image(
//            painter = painterResource(id = R.mipmap.background_login),
//            contentDescription = "Background image",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.fillMaxSize()
//        )
//    }
//}

@Composable
fun Splash(alpha: Float) {
    Box(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.Black else Purple700)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(120.dp)
                .alpha(alpha = alpha),
            imageVector = Icons.Default.Email,
            contentDescription = "Logo Icon",
            tint = Color.White
        )
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    Splash(alpha = 1f)
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun SplashScreenDarkPreview() {
    Splash(alpha = 1f)
}