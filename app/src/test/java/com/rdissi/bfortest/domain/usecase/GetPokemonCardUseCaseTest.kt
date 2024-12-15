package com.rdissi.bfortest.domain.usecase

import app.cash.turbine.test
import com.rdissi.bfortest.common.Result
import com.rdissi.bfortest.domain.model.PokemonCard
import com.rdissi.bfortest.domain.repository.PokemonRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

@RunWith(MockitoJUnitRunner::class)
class GetPokemonCardUseCaseTest {

    @Mock
    private lateinit var pokemonRepository: PokemonRepository

    @Test
    fun `get pokemon card form GetPokemonCardUseCase with Success`() = runTest {
        // Given
        val pokemonName = "ditto"

        val expectedId = 123456
        val expectedPokemonName = "ditto"
        val expectedImageUrl = "https://www.imageurl.com"
        val expectedOrder = 1
        val expectedWeight = 4

        val pokemonCardMock = mock<PokemonCard> {
            on(it.id).thenReturn(expectedId)
            on(it.name).thenReturn(expectedPokemonName)
            on(it.imageUrl).thenReturn(expectedImageUrl)
            on(it.order).thenReturn(expectedOrder)
            on(it.weight).thenReturn(expectedWeight)
        }

        given(pokemonRepository.getPokemonByName(pokemonName)).willReturn(pokemonCardMock)

        //When
        GetPokemonCardUseCase(pokemonRepository).invoke(pokemonName).test {

            assertEquals(Result.Loading, awaitItem())

            val result = awaitItem()
            assertTrue(result is Result.Success)
            assertEquals(expectedId, (result as Result.Success).data.id)
            assertEquals(expectedPokemonName, result.data.name)
            assertEquals(expectedImageUrl, result.data.imageUrl)
            assertEquals(expectedOrder, result.data.order)
            assertEquals(expectedWeight, result.data.weight)

            awaitComplete()
        }
    }

    @Test
    fun `GetPokemonCardUseCase return an error when the repository associated return a pokemon card null`() = runTest {
        // Given
        val pokemonName = "ditto"
        given(pokemonRepository.getPokemonByName(pokemonName)).willReturn(null)

        //When
        GetPokemonCardUseCase(pokemonRepository).invoke(pokemonName).test {

            assertEquals(Result.Loading, awaitItem())

            val result = awaitItem()
            assertTrue(result is Result.Error)
            cancelAndConsumeRemainingEvents()
        }
    }

}