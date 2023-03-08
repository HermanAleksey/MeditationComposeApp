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

const val ANIMATION_DURATION = 400

@SuppressLint("ComposableNaming")
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun MeditationNavHostEngine() =
    rememberAnimatedNavHostEngine(
        navHostContentAlignment = Alignment.Center,
        rootDefaultAnimations = RootNavGraphDefaultAnimations(
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { width -> -width },
                    animationSpec = tween(ANIMATION_DURATION)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = {  width -> width },
                    animationSpec = tween(ANIMATION_DURATION)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = {  width -> width },
                    animationSpec = tween(ANIMATION_DURATION)
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { width -> -width },
                    animationSpec = tween(ANIMATION_DURATION)
                )
            },
        )
    )