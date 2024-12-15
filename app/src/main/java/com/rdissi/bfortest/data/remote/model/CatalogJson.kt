package com.rdissi.bfortest.data.remote.model

import com.google.gson.annotations.SerializedName

data class CatalogJson(
    val count: Int,
    val next: String?,
    val previous: String?,

    @SerializedName("results")
    val pokemonsJson: List<PokemonJson>
)
