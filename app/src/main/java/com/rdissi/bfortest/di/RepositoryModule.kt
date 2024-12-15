package com.rdissi.bfortest.di

import com.rdissi.bfortest.data.repository.CatalogRepositoryImpl
import com.rdissi.bfortest.data.repository.PokemonRepositoryImpl
import com.rdissi.bfortest.domain.repository.PokemonRepository
import com.rdissi.bfortest.domain.repository.CatalogRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModuleModule {

    @Singleton
    @Binds
    abstract fun bindPokemonRepository(impl: PokemonRepositoryImpl): PokemonRepository

    @Singleton
    @Binds
    abstract fun bindCatalogRepository(impl: CatalogRepositoryImpl): CatalogRepository
}