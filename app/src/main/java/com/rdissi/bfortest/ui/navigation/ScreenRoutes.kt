package com.rdissi.bfortest.ui.navigation

import androidx.annotation.StringRes
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.rdissi.bfortest.R

sealed class ScreenRoutes(
    val route: String,
    val routeWithArgs: String = "",
    val arguments: List<NamedNavArgument> = emptyList(),
    @StringRes val title: Int
) {

    data object Home: ScreenRoutes(
        route = "home_screen",
        title = R.string.author
    )

    data object PokemonDetails: ScreenRoutes(
        route = "pokemon_details_screen",
        routeWithArgs = "pokemon_details_screen/{name}",
        arguments = listOf(
            navArgument("name") { type = NavType.StringType }
        ),
        title = R.string.pokemon_card,
    )

}
