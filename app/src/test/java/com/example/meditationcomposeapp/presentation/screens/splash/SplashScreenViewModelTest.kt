package com.example.meditationcomposeapp.presentation.screens.splash

import com.example.meditationcomposeapp.data_source.data_store.UserDataStore
import com.example.meditationcomposeapp.data_source.data_store.UserDataStoreImpl
import com.example.meditationcomposeapp.data_source.repository.update_description.UpdateDescriptionRepository
import com.example.meditationcomposeapp.model.usecase.authentication.LoginUseCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SplashScreenViewModelTest {

    @Mock
    private lateinit var userDataStore: UserDataStore

    @Mock
    private lateinit var loginUseCase: LoginUseCase

    @Mock
    private lateinit var updateDescriptionRepository: UpdateDescriptionRepository

    private lateinit var viewModel: SplashScreenViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = SplashScreenViewModel(
            userDataStore, loginUseCase, updateDescriptionRepository
        )
    }

    @Test
    fun test_1() {
        val a = viewModel.test_1()

        assert(a == 1)
//        whenever(userDataStore).th
    }
}