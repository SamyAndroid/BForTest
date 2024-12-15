package com.rdissi.bfortest.data.remote.service

import com.rdissi.bfortest.data.remote.model.CatalogJson
import com.rdissi.bfortest.data.remote.model.PokemonCardJson
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon")
    suspend fun getCatalog(@Query("limit") limit: Int, @Query("offset") offset: Int): CatalogJson?

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name: String): PokemonCardJson?
}