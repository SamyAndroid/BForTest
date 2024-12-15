package com.rdissi.bfortest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rdissi.bfortest.data.source.RemoteDataSource
import com.rdissi.bfortest.data.source.CatalogPagingDataSource
import com.rdissi.bfortest.di.IoDispatcher
import com.rdissi.bfortest.domain.model.Pokemon
import com.rdissi.bfortest.domain.repository.CatalogRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CatalogRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : CatalogRepository {

    override fun getCatalog(): Flow<PagingData<Pokemon>> = Pager(
            config = PagingConfig(pageSize = 8),
            pagingSourceFactory = { CatalogPagingDataSource(remoteDataSource) }
        ).flow
        .flowOn(dispatcher)
}
