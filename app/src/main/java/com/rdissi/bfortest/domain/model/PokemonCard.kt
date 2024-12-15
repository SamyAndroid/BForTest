package com.rdissi.bfortest.domain.model

data class PokemonCard(
    val id: Int,
    val name: String,
    val order: Int,
    val weight: Int,
    val abilities: List<String>,
    val imageUrl: String
)