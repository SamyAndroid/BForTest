package com.rdissi.bfortest.domain.model

data class Catalog(
    val totalSize: Int,
    val nextPageUrl: String?,
    val previousPageUrl: String?,
    val pokemons: List<Pokemon>
)
