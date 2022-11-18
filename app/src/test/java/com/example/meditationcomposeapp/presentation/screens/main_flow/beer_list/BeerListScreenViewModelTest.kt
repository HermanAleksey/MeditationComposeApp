package com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list

import com.example.meditationcomposeapp.CoroutinesTestRule
import com.example.meditationcomposeapp.model.usecase.authentication.SetNewPasswordUseCase
import com.example.meditationcomposeapp.model.usecase.punk.GetBeersUseCase
import com.example.meditationcomposeapp.presentation.screens.login_flow.new_password.NewPasswordScreenViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class BeerListScreenViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var rule = CoroutinesTestRule()

    @Mock
    private lateinit var getBeerListUseCase: GetBeersUseCase

    private lateinit var viewModel: BeerListScreenViewModel

    @Before
    fun setup() {
        viewModel = BeerListScreenViewModel(getBeerListUseCase)
    }

    @Test
    fun `onBeerItemClicked, navigate`(){
        var navigated = false

        viewModel.onBeerItemClicked { navigated = true }

        assert(navigated)
    }

}