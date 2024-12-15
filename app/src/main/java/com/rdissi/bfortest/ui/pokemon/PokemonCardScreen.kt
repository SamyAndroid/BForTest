package com.rdissi.bfortest.ui.pokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rdissi.bfortest.R
import com.rdissi.bfortest.domain.model.PokemonCard
import com.rdissi.bfortest.ui.component.SideEffectWithLifeCycle
import com.rdissi.bfortest.ui.component.showToast
import com.rdissi.bfortest.ui.error.ErrorScreen
import com.rdissi.bfortest.ui.pokemon.PokemonCardViewModel.UiState
import com.rdissi.bfortest.ui.toolkit.PokemonResultPreviewParameterProvider

@Composable
fun PokemonCardScreen(
    modifier: Modifier = Modifier,
    pokemonName: String,
    pokemonCardViewModel: PokemonCardViewModel = hiltViewModel(),
) {
    val uiState by pokemonCardViewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(key1 = pokemonCardViewModel) {
        pokemonCardViewModel.uiEvent.collect { event ->
            when (event) {
                is PokemonCardViewModel.UiEvent.Toast -> showToast(context, event.message)
            }
        }
    }
    SideEffectWithLifeCycle(
        lifecycleOwner = lifecycleOwner,
        onStart = { pokemonCardViewModel.getPokemonByName(pokemonName) },
    )
    PokemonResult(
        modifier = modifier,
        uiState = uiState,
        onShareClick = { pokemonCard ->
            pokemonCardViewModel.sharePokemon(context, pokemonCard)
        },
    )
}

@Composable
fun PokemonResult(
    modifier: Modifier = Modifier,
    uiState: UiState,
    onShareClick: (PokemonCard) -> Unit,
) {
    Box(
        modifier =
            modifier
                .fillMaxSize()
                .background(Color.White),
    ) {
        when (uiState) {
            UiState.Loading -> {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
            is UiState.Success -> {
                PokemonCardPage(
                    pokemonCard = uiState.pokemonCard,
                    onShareClick = onShareClick,
                )
            }
            is UiState.Error -> {
                ErrorScreen(message = stringResource(id = R.string.pokemoncard_error))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PokemonCardScreenPreview(
    @PreviewParameter(PokemonResultPreviewParameterProvider::class) uiState: UiState,
) {
    PokemonResult(
        uiState = uiState,
        onShareClick = {},
    )
}
