package com.rdissi.bfortest.ui.toolkit

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.rdissi.bfortest.domain.model.PokemonCard
import com.rdissi.bfortest.ui.pokemon.PokemonCardViewModel

class PokemonResultPreviewParameterProvider : PreviewParameterProvider<PokemonCardViewModel.UiState> {
    override val values = sequenceOf(
        PokemonCardViewModel.UiState.Loading,
        PokemonCardViewModel.UiState.Success(
            PokemonCard(
            id = 0,
            name = "pokemonName",
            order = 1,
            weight = 3,
            listOf("power1", "power2"),
            imageUrl = "")
        ),
        PokemonCardViewModel.UiState.Error("An error occurred")
    )
}