package com.example.meditationcomposeapp.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine

@SuppressLint("ComposableNaming")
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun MeditationNavHostEngine(screenWidth: Int) =
    rememberAnimatedNavHostEngine(
        navHostContentAlignment = Alignment.Center,
        rootDefaultAnimations = RootNavGraphDefaultAnimations(
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -screenWidth },
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { screenWidth },
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { screenWidth },
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -screenWidth },
                    animationSpec = tween(700)
                )
            },
        )
    )