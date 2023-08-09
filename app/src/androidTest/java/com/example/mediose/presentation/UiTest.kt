package com.justparokq.mediose.presentation

import androidx.annotation.StringRes
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule

open class UiTest {

    @get:Rule
    val testRule = createAndroidComposeRule(MainActivity::class.java)

    protected fun getStringRes(@StringRes resId: Int): String {
        return testRule.activity.getString(resId)
    }
}