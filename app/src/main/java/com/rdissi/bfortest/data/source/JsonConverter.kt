package com.rdissi.bfortest.data.source

import com.rdissi.bfortest.data.remote.model.AbilityHolderJson
import com.rdissi.bfortest.data.remote.model.CatalogJson
import com.rdissi.bfortest.data.remote.model.PokemonCardJson
import com.rdissi.bfortest.data.remote.model.PokemonJson
import com.rdissi.bfortest.domain.model.Catalog
import com.rdissi.bfortest.domain.model.Pokemon
import com.rdissi.bfortest.domain.model.PokemonCard

object JsonConverter {

    fun CatalogJson.toCatalog() = Catalog(
        totalSize = this.count,
        nextPageUrl = this.next ?: "",
        previousPageUrl = this.previous ?: "",
        pokemons = this.pokemonsJson.toPokemons(),
    )

    fun PokemonCardJson.toPokemonCard() = PokemonCard(
        id = this.id,
        name = this.name,
        order =  this.order,
        weight = this.weight,
        abilities =  this.abilitiesHolderJson.toAbilities(),
        imageUrl = this.spritesJson.otherJson.imageJson.defaultImageUrl
    )

    private fun List<AbilityHolderJson>.toAbilities(): List<String> {
        return map {
            it.ability.name
        }
    }

    private fun PokemonJson.toPokemon() = Pokemon(
        name = this.name,
        url = this.url,
    )

    private fun List<PokemonJson>.toPokemons(): List<Pokemon> {
        return map {
            it.toPokemon()
        }
    }
}
