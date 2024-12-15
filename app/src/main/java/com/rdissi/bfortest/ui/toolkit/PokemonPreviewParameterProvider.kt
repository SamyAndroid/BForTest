package com.rdissi.bfortest.ui.toolkit

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.rdissi.bfortest.domain.model.Pokemon

class PokemonPreviewParameterProvider : PreviewParameterProvider<Pokemon> {
    override val values = sequenceOf(
        MockDataSource.getFirstPokemonMock()
    )
}