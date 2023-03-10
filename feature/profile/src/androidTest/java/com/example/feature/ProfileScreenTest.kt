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


class ProfileScreenTest {

    @Rule
    @JvmField
    var composeTestRule: ComposeContentTestRule = createComposeRule()
//     use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity

    @Test
    fun myTest() {
        // Start the app
        composeTestRule.setContent {
            AppTheme {
                ProfileScreen(viewModel = ProfileScreenViewModel(
                    clearAuthDataUseCase = object : ClearAuthDataUseCase {
                        override suspend fun invoke() {
                            //mock
                        }
                    }
                ))
            }
        }

        composeTestRule.onNodeWithText(
            text = "LOG OUT",
            ignoreCase = true
        ).assertIsDisplayed()
    }
}