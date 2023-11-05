package com.justparokq.core.coroutines_test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class CoroutinesTestRule(
    val testDispatcher: TestDispatcher = StandardTestDispatcher()
) :TestWatcher() {

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}