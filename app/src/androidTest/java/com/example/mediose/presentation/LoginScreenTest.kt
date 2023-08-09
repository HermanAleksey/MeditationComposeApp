package com.justparokq.mediose.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Test
import com.example.feature.authentication.R as authR

@Suppress("SimpleRedundantLet")
internal class LoginScreenTest : UiTest() {

    @Before
    fun openScreen() {
        // navigate to screen
        testRule.onNode(
            hasText(getStringRes(authR.string.login_button)),
            useUnmergedTree = true
        ).let { enterScreenLoginButton ->
            enterScreenLoginButton.assertIsDisplayed()
            enterScreenLoginButton.performClick()
        }
    }

    @Test
    fun verify_loginScreenHasAllLabels() {
        testRule.onNode(
            hasText(getStringRes(authR.string.sign_in)),
            useUnmergedTree = true
        ).let { signInLabel ->
            signInLabel.assertIsDisplayed()
        }
        testRule.onNode(
            hasText(getStringRes(authR.string.sign_in_desc)),
            useUnmergedTree = true
        ).let { signInDescription ->
            signInDescription.assertIsDisplayed()
        }
        testRule.onNode(
            hasText(getStringRes(authR.string.email_address)),
            useUnmergedTree = true
        ).let { emailAddressField ->
            emailAddressField.assertIsDisplayed()
        }
        testRule.onNode(
            hasText(getStringRes(authR.string.password)),
            useUnmergedTree = true
        ).let { emailAddressField ->
            emailAddressField.assertIsDisplayed()
        }
        testRule.onNode(
            hasText(getStringRes(authR.string.forgot_password)),
            useUnmergedTree = true
        ).let { forgotPasswordLabel ->
            forgotPasswordLabel.assertIsDisplayed()
        }
        testRule.onNode(
            hasText(getStringRes(authR.string.login), ignoreCase = true),
            useUnmergedTree = true
        ).let { loginButton ->
            loginButton.assertIsDisplayed()
        }
        testRule.onNode(
            hasText(getStringRes(authR.string.sign_up), substring = true),
            useUnmergedTree = true
        ).let { signUpLabel ->
            signUpLabel.assertIsDisplayed()
        }
    }

    @Test
    fun verify_signUpNavigateToSignUpScreen() {
        // click on assert
        testRule.onNode(
            hasText(getStringRes(authR.string.sign_up), substring = true),
            useUnmergedTree = true
        ).let { signUpLabel ->
            signUpLabel.assertIsDisplayed()
            signUpLabel.performClick()
        }
        // sign up screen is displayed
        testRule.onNode(
            hasTestTag(getStringRes(authR.string.registration_screen_test_tag)),
            useUnmergedTree = true
        ).let { signUpScreen ->
            signUpScreen.assertIsDisplayed()
        }
    }

    @Test
    fun verify_ForgotPasswordNavigateRestoreScreen_andSetLoginValue() {
        val inputtedLogin = "parokq@github.com"
        // input login
        testRule.onNode(
            hasTestTag(getStringRes(authR.string.login_field_login_screen_test_tag)),
            useUnmergedTree = true
        ).let { loginInputField ->
            loginInputField.assertIsDisplayed()
            loginInputField.performTextInput(inputtedLogin)
        }
        // click forgot password
        testRule.onNode(
            hasText(getStringRes(authR.string.forgot_password)),
            useUnmergedTree = true
        ).let { signUpLabel ->
            signUpLabel.assertIsDisplayed()
            signUpLabel.performClick()
        }

        // sign up screen is displayed
        testRule.onNode(
            hasTestTag(getStringRes(authR.string.enter_login_screen_test_tag)),
            useUnmergedTree = true
        ).let { passwordRecoveryScreen ->
            passwordRecoveryScreen.assertIsDisplayed()
        }
        // login inputted into recovery screen
        testRule.onNode(
            hasTestTag(getStringRes(authR.string.login_field_enter_login_screen_test_tag)),
            useUnmergedTree = true
        ).let { loginInputField ->
            loginInputField.assertIsDisplayed()
            loginInputField.assertTextEquals(inputtedLogin)
        }
    }
}