package com.rdissi.bfortest.domain.repository

import com.rdissi.bfortest.domain.model.PokemonCard

interface PokemonRepository {
    suspend fun getPokemonByName(name: String): PokemonCard?
}
