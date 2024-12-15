package com.rdissi.bfortest.domain.usecase

import android.util.Log
import com.rdissi.bfortest.common.Result
import com.rdissi.bfortest.domain.model.PokemonCard
import com.rdissi.bfortest.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPokemonCardUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
     operator fun invoke(pokemonName: String): Flow<Result<PokemonCard>> = flow {
        emit(Result.Loading)
        runCatching {
            val pokemonCard: PokemonCard = pokemonRepository.getPokemonByName(pokemonName) ?: throw Exception("pokemonCard null")
            pokemonCard
        }.onSuccess { pokemonCard ->
            emit(Result.Success(pokemonCard))
        }.onFailure {
            emit(Result.Error("Error=$it"))
            Log.e("GetPokemonCardUseCase", it.toString())
        }
    }
}
