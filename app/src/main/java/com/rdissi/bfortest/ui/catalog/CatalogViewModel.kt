package com.rdissi.bfortest.ui.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rdissi.bfortest.domain.model.Pokemon
import com.rdissi.bfortest.domain.usecase.CatalogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val catalogUseCase: CatalogUseCase
) : ViewModel() {

    private val _catalogState: MutableStateFlow<PagingData<Pokemon>> = MutableStateFlow(value = PagingData.empty())
    val catalogState: StateFlow<PagingData<Pokemon>> = _catalogState.asStateFlow()

    init {
        getCatalog()
    }

    private fun getCatalog() {
        catalogUseCase()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .onEach { page: PagingData<Pokemon> ->
                _catalogState.update { page }
            }.launchIn(viewModelScope)
    }

    //Maybe use directly the domain flow
    /*val catalogState: Flow<PagingData<Pokemon>> =
        catalogUseCase()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)*/

}
