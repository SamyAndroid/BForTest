package com.rdissi.bfortest.ui.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.rdissi.bfortest.R
import com.rdissi.bfortest.domain.model.Pokemon
import com.rdissi.bfortest.ui.component.PokemonItem

@Composable
fun CatalogPage(
    modifier: Modifier = Modifier,
    catalogViewModel: CatalogViewModel = hiltViewModel(),
    onItemClick: (Pokemon) -> Unit = {},
) {
    val catalogPager = catalogViewModel.catalogState.collectAsLazyPagingItems()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        if (catalogPager.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
            )
        } else {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(catalogPager.itemCount) { index ->
                    PokemonItem(pokemon = catalogPager[index]!!, onItemClick = onItemClick)
                    HorizontalDivider()
                }

                when(catalogPager.loadState.append) {
                    is LoadState.Loading -> item {
                        CircularProgressIndicator(modifier = Modifier.padding(8.dp))
                    }
                    is LoadState.Error -> item {
                        Text(stringResource(id = R.string.loading_error), color = Color.Red)
                    }
                    is LoadState.NotLoading -> {}
                }

            }
        }
    }
}
