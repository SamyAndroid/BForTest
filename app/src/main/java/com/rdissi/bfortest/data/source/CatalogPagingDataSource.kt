package com.rdissi.bfortest.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rdissi.bfortest.data.source.JsonConverter.toCatalog
import com.rdissi.bfortest.domain.model.Pokemon
import kotlinx.coroutines.delay
import javax.inject.Inject

class CatalogPagingDataSource @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : PagingSource<Int, Pokemon>() {

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition
    }

    //TODO: remove the delay(1000L). The ws is so fast we can't see the bottom progress bar inside the list
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            delay(1000L)
            val pageSize = 20
            val currentOffset = params.key ?: 0

            val catalog = remoteDataSource.fetchCatalog(limit = pageSize, offset = currentOffset)?.toCatalog()
            catalog?.let {
                LoadResult.Page(
                    data = catalog.pokemons,
                    prevKey = null,
                    nextKey = currentOffset + pageSize
                )
            } ?: throw Exception("Catalog fetch error")

        } catch (e : Exception){
            LoadResult.Error(e)
        }
    }
}