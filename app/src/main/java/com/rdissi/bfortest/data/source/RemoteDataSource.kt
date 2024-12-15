package com.rdissi.bfortest.data.source

import com.rdissi.bfortest.data.remote.model.CatalogJson
import com.rdissi.bfortest.data.remote.model.PokemonCardJson
import com.rdissi.bfortest.data.remote.service.ApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
) {
    suspend fun fetchCatalog(limit:Int, offset: Int): CatalogJson? = apiService.getCatalog(limit, offset)
    suspend fun getPokemonByName(name: String): PokemonCardJson? = apiService.getPokemonByName(name)
}
