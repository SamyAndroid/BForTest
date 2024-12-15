package com.rdissi.bfortest.domain.usecase

import androidx.paging.PagingData
import com.rdissi.bfortest.domain.model.Pokemon
import com.rdissi.bfortest.domain.repository.CatalogRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CatalogUseCase @Inject constructor(
    private val catalogRepository: CatalogRepository
) {
    operator fun invoke(): Flow<PagingData<Pokemon>> = catalogRepository.getCatalog()
}
