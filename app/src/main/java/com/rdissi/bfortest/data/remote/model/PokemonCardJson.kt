package com.rdissi.bfortest.data.remote.model

import com.google.gson.annotations.SerializedName

data class PokemonCardJson(
    val id: Int,
    val name: String,
    val order: Int,
    val weight: Int,

    @SerializedName("abilities")
    val abilitiesHolderJson: List<AbilityHolderJson>,

    @SerializedName("sprites")
    val spritesJson: SpritesJson
)