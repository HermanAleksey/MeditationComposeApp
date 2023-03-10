package com.example.feature

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.core.data_store.use_case.ClearAuthDataUseCase
import com.example.design_system.AppTheme
import com.example.feature.profile.api.ProfileScreen
import com.example.feature.profile.api.ProfileScreenViewModel
import org.junit.Rule
import org.junit.Test


// ./gradlew connectedAndroidTest (to run all tests)
class ProfileScreenTest {

    @get:Rule(order = 0)
    var composeTestRule: ComposeContentTestRule = createComposeRule()

    private val clearAuthDataUseCase = object : ClearAuthDataUseCase {
        override suspend fun invoke() {
            //do nothing
        }
    }

    @Test
    fun openScreen_LogOutButtonVisible() {
        // Start the app
        composeTestRule.setContent {
            AppTheme {
                ProfileScreen(
                    viewModel = ProfileScreenViewModel(
                        clearAuthDataUseCase = clearAuthDataUseCase
                    ),
                )
            }
        }

        composeTestRule.onNodeWithText(
            text = "LOG OUT",
            ignoreCase = true
        ).assertIsDisplayed()
    }
}