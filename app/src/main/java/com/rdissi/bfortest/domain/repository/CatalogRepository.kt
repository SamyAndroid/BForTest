package com.rdissi.bfortest.domain.repository

import androidx.paging.PagingData
import com.rdissi.bfortest.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface CatalogRepository {
     fun getCatalog(): Flow<PagingData<Pokemon>>
}
