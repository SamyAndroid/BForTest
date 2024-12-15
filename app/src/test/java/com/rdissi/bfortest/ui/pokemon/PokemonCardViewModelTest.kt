package com.rdissi.bfortest.ui.pokemon

import app.cash.turbine.test
import com.nhaarman.mockito_kotlin.given
import com.rdissi.bfortest.domain.usecase.GetPokemonCardUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import com.rdissi.bfortest.common.Result
import com.rdissi.bfortest.domain.model.PokemonCard
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class PokemonCardViewModelTest {

    @Mock
    private lateinit var getPokemonCardUseCase: GetPokemonCardUseCase

    private lateinit var pokemonCardViewModel: PokemonCardViewModel

    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getPokemonByName will return an UI success state`(): Unit = runTest {
        //Given
        val pokemonName = "ditto"
        val pokemonCard = mock<PokemonCard>()
        val resultFlow = flowOf(
            Result.Loading,
            Result.Success(pokemonCard)
        )

        given(getPokemonCardUseCase.invoke(pokemonName)).willReturn(resultFlow)
        pokemonCardViewModel = PokemonCardViewModel(getPokemonCardUseCase)

        //When
        pokemonCardViewModel.getPokemonByName(pokemonName)

        getPokemonCardUseCase(pokemonName).test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(pokemonCard), awaitItem())
            awaitComplete()
        }

        //Then
        pokemonCardViewModel.uiState.test {
            assertEquals(PokemonCardViewModel.UiState.Success(pokemonCard), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `getPokemonByName will return an UI error state`(): Unit = runTest {
        //Given
        val pokemonName = ""
        val errorMessage = "An error has occurred"
        val resultFlow = flowOf(
            Result.Loading,
            Result.Error(errorMessage)
        )

        given(getPokemonCardUseCase.invoke(pokemonName)).willReturn(resultFlow)
        pokemonCardViewModel = PokemonCardViewModel(getPokemonCardUseCase)

        //When
        pokemonCardViewModel.getPokemonByName(pokemonName)

        getPokemonCardUseCase(pokemonName).test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Error(errorMessage), awaitItem())
            awaitComplete()
        }

        //Then
        pokemonCardViewModel.uiState.test {
            assertEquals(PokemonCardViewModel.UiState.Error(errorMessage), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }
}