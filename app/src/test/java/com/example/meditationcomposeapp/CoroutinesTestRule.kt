package com.example.meditationcomposeapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class CoroutinesTestRule : TestWatcher() {

    private val dispatcher = StandardTestDispatcher()
    val scope = TestScope(dispatcher)

    override fun starting(description: Description) {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.openMocks(this)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}