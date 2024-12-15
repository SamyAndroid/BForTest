package com.rdissi.bfortest.data.repository

import android.util.Log
import com.rdissi.bfortest.data.source.RemoteDataSource
import com.rdissi.bfortest.data.source.JsonConverter.toPokemonCard
import com.rdissi.bfortest.di.IoDispatcher
import com.rdissi.bfortest.domain.model.PokemonCard
import com.rdissi.bfortest.domain.repository.PokemonRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : PokemonRepository {

    override suspend fun getPokemonByName(name: String)= withContext(dispatcher) {
        try {
            val pokemonCard: PokemonCard? = remoteDataSource.getPokemonByName(name)?.toPokemonCard()
            pokemonCard
        } catch (exception: Exception) {
            Log.e("PokemonRepositoryImpl", "getPokemonByName()", exception)
            throw exception
        }
    }
}
