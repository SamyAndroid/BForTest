package com.rdissi.bfortest.ui.pokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.rdissi.bfortest.R
import com.rdissi.bfortest.domain.model.PokemonCard
import com.rdissi.bfortest.ui.component.BigTitle
import com.rdissi.bfortest.ui.component.HeaderImage
import com.rdissi.bfortest.ui.component.Tag
import com.rdissi.bfortest.ui.theme.LightGrey

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PokemonCardPage(
    pokemonCard: PokemonCard,
    onShareClick: (PokemonCard) -> Unit,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(LightGrey)
            .padding(horizontal = 16.dp)
            .verticalScroll(state = rememberScrollState()),
    ) {
        val (headerImage, tag, title, icon, caption, abilities, description) = createRefs()

        HeaderImage(imageUrl = pokemonCard.imageUrl, reference = headerImage) {
            top.linkTo(parent.top, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }

        Tag(label = pokemonCard.name, reference = tag) {
            start.linkTo(headerImage.start, margin = 16.dp)
            bottom.linkTo(headerImage.bottom)
            top.linkTo(headerImage.bottom)
        }

        BigTitle(title = stringResource(id = R.string.description), reference = title) {
            start.linkTo(parent.start)
            top.linkTo(tag.bottom, margin = 16.dp)
            end.linkTo(headerImage.end)
            width = Dimension.fillToConstraints
        }

        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = "share",
            modifier = Modifier
                .constrainAs(icon) {
                    top.linkTo(title.top)
                    end.linkTo(parent.end, margin = 8.dp)
                    width = Dimension.fillToConstraints
                }
                .clickable {
                    onShareClick(pokemonCard)
                }
                .size(28.dp)
        )

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                ) {
                    append("By")
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 14.sp,
                        color = Color(red = 65, green = 160, blue = 253)
                    )
                ) {
                    append(" ")
                    append(stringResource(id = R.string.pokemon_team))
                }

            },
            modifier = Modifier.constrainAs(caption) {
                start.linkTo(parent.start)
                top.linkTo(title.bottom, margin = 8.dp)
                end.linkTo(headerImage.end, margin = 16.dp)
                width = Dimension.fillToConstraints
            }
        )

        FlowRow(
            modifier = Modifier
                .constrainAs(abilities) {
                    start.linkTo(parent.start)
                    top.linkTo(caption.bottom, margin = 8.dp)
                    end.linkTo(headerImage.end)
                    width = Dimension.fillToConstraints
                },
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            pokemonCard.abilities.forEach {
                FilterChip(
                    selected = false,
                    onClick = {},
                    label = { Text(it) }
                )
            }
        }
        Text(
            text = stringResource(id = R.string.large_text),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier.constrainAs(description) {
                start.linkTo(parent.start)
                top.linkTo(abilities.bottom, margin = 8.dp)
                end.linkTo(headerImage.end)
                width = Dimension.fillToConstraints
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PokemonCardPagePreview() {
    PokemonCardPage(
        pokemonCard = PokemonCard(
            id = 0,
            name = "pokemonName",
            order = 1,
            weight = 3,
            listOf("power1", "power2", "power3", "power4", "power5"),
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/1.svg"
        ),
        onShareClick = {}
    )
}