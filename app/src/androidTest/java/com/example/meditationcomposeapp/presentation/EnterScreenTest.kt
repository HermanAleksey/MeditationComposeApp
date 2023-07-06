package com.example.meditationcomposeapp.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.performClick
import org.junit.Test
import com.example.feature.authentication.R as authR

@Suppress("SimpleRedundantLet")
internal class EnterScreenTest : UiTest() {

    @Test
    fun verify_enterScreenHasAllLabels() {
        // enter screen test
        testRule.onNode(
            hasTestTag(getStringRes(authR.string.enter_screen_test_tag)),
            useUnmergedTree = true
        ).let { enterScreen ->
            enterScreen.assertIsDisplayed()
        }
        testRule.onNode(
            hasText(getStringRes(authR.string.welcome)),
            useUnmergedTree = true
        ).let { welcomeLabel ->
            welcomeLabel.assertIsDisplayed()
        }
        testRule.onNode(
            hasText(getStringRes(authR.string.welcome_text)),
            useUnmergedTree = true
        ).let { welcomeLabel ->
            welcomeLabel.assertIsDisplayed()
        }
        testRule.onNode(
            hasText(getStringRes(authR.string.login_button)),
            useUnmergedTree = true
        ).let { enterScreenLoginButton ->
            enterScreenLoginButton.assertIsDisplayed()
        }
        testRule.onNode(
            hasText(getStringRes(authR.string.sign_up), substring = true),
            useUnmergedTree = true
        ).let { enterScreenLoginButton ->
            enterScreenLoginButton.assertIsDisplayed()
        }
    }

    @Test
    fun verify_enterScreen_canNavigateLoginAndBackwards() {
        // enter screen displayed
        testRule.onNode(
            hasTestTag(getStringRes(authR.string.enter_screen_test_tag)),
            useUnmergedTree = true
        ).let { enterScreen ->
            enterScreen.assertIsDisplayed()
        }
        // click on login button
        testRule.onNode(
            hasText(getStringRes(authR.string.login_button)),
            useUnmergedTree = true
        ).let { enterScreenLoginButton ->
            enterScreenLoginButton.assertIsDisplayed()
            enterScreenLoginButton.performClick()
        }

        // displayed login screen
        testRule.onNode(
            hasTestTag(getStringRes(authR.string.login_screen_test_tag)),
            useUnmergedTree = true
        ).let { loginScreen ->
            loginScreen.assertIsDisplayed()
        }
        // todo как тестировать бэк клик?
        // back pressed
//        composeTestRule.activity.onBackPressedDispatcher.onBackPressed()
//        //enter screen displayed
//        composeTestRule.onNode(
//            hasTestTag(getStringRes(authR.string.enter_screen_test_tag)),
//            useUnmergedTree = true
//        ).let { enterScreen ->
//            enterScreen.assertIsDisplayed()
//        }
    }

    @Test
    fun verify_enterScreen_canNavigateSignUpAndBackwards() {
        // enter screen displayed
        testRule.onNode(
            hasTestTag(getStringRes(authR.string.enter_screen_test_tag)),
            useUnmergedTree = true
        ).let { enterScreen ->
            enterScreen.assertIsDisplayed()
        }
        // click sign up
        testRule.onNode(
            hasText(getStringRes(authR.string.sign_up), substring = true),
            useUnmergedTree = true
        ).let { enterScreenLoginButton ->
            enterScreenLoginButton.assertIsDisplayed()
            enterScreenLoginButton.performClick()
        }

        // displayed sign up screen
        testRule.onNode(
            hasTestTag(getStringRes(authR.string.registration_screen_test_tag)),
            useUnmergedTree = true
        ).let { loginScreen ->
            loginScreen.assertIsDisplayed()
        }
        // todo разобраться
//        //back pressed
//        composeTestRule.activity.onBackPressedDispatcher.onBackPressed()
//        //enter screen displayed
//        composeTestRule.onNode(
//            hasTestTag(getStringRes(authR.string.enter_screen_test_tag)),
//            useUnmergedTree = true
//        ).let { enterScreen ->
//            enterScreen.assertIsDisplayed()
//        }
    }
}