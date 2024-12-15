package com.rdissi.bfortest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rdissi.bfortest.ui.catalog.CatalogPage
import com.rdissi.bfortest.ui.pokemon.PokemonCardScreen

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ScreenRoutes.Home.route,
    ) {
        // Pokemon list + paging
        composable(route = ScreenRoutes.Home.route) {
            CatalogPage(
                onItemClick = { pokemon ->
                    navController.navigate(route = ScreenRoutes.PokemonCard.route + "/" + pokemon.name)
                },
            )
        }
        // Pokemon card screen
        composable(
            route = ScreenRoutes.PokemonCard.routeWithArgs,
            arguments = ScreenRoutes.PokemonCard.arguments,
        ) { navBackStackEntry ->
            val pokemonName = navBackStackEntry.arguments?.getString("name") ?: ""
            PokemonCardScreen(pokemonName = pokemonName)
        }
    }
}
