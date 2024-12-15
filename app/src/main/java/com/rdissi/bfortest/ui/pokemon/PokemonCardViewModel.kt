package com.rdissi.bfortest.ui.pokemon

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rdissi.bfortest.common.Result
import com.rdissi.bfortest.domain.model.PokemonCard
import com.rdissi.bfortest.domain.usecase.GetPokemonCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.launchIn

@HiltViewModel
class PokemonCardViewModel @Inject constructor(
    private val getPokemonCardUseCase: GetPokemonCardUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    sealed class UiState {
        data object Loading : UiState()
        data class Success(val pokemonCard: PokemonCard) : UiState()
        data class Error(val message: String?) : UiState()
    }

    sealed class UiEvent {
        data class Toast(val message: String) : UiEvent()
    }

    fun getPokemonByName(name: String) {
        getPokemonCardUseCase(name).onEach { result ->
            when (result) {
                is Result.Loading -> _uiState.update { UiState.Loading }
                is Result.Success -> _uiState.update { UiState.Success(pokemonCard = result.data) }
                is Result.Error -> {
                    _uiState.update { UiState.Error(result.message) }
                    _uiEvent.emit(UiEvent.Toast(result.message))
                }
            }
        }.launchIn(viewModelScope)
    }

    fun sharePokemon(context: Context, pokemonCard: PokemonCard) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, pokemonCard.name)
            putExtra(Intent.EXTRA_TITLE, "Pokemon ${pokemonCard.name}")
        }
        val shareIntent = Intent.createChooser(sendIntent, "Share Pokemon")
        context.startActivity(shareIntent)
    }
}
