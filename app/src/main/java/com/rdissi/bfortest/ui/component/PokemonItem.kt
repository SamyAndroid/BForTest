package com.rdissi.bfortest.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rdissi.bfortest.domain.model.Pokemon
import com.rdissi.bfortest.ui.toolkit.PokemonPreviewParameterProvider

@Composable
fun PokemonItem(
    pokemon: Pokemon,
    onItemClick: (Pokemon) -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable {
                    onItemClick(pokemon)
                }.padding(20.dp),
    ) {
        Text(
            text = pokemon.name.capitalize(Locale.current),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonItemPreview(
    @PreviewParameter(PokemonPreviewParameterProvider::class) pokemon: Pokemon,
) {
    PokemonItem(pokemon = pokemon, onItemClick = {})
}
