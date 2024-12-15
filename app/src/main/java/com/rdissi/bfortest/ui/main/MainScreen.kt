package com.rdissi.bfortest.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rdissi.bfortest.R
import com.rdissi.bfortest.ui.component.MyDialog
import com.rdissi.bfortest.ui.component.MyTopAppBar
import com.rdissi.bfortest.ui.navigation.Navigation
import com.rdissi.bfortest.ui.navigation.ScreenRoutes

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val context = LocalContext.current
    val topAppBarInfo = updateTopBar(navController)
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            if (topAppBarInfo.isVisible) {
                MyTopAppBar(
                    title = topAppBarInfo.title,
                    canNavigateBack = topAppBarInfo.showBackIcon,
                    navigateUp = {
                        navController.navigateUp()
                    },
                    showActionIcons = topAppBarInfo.showShareIcon,
                    onShareClick = {
                        viewModel.shareMyTest(context)
                    },
                    onInfoClick = {
                        showDialog = true
                    }
                )
            }
        },
    ) { innerPadding ->
        Navigation(
            modifier = modifier.padding(innerPadding),
            navController = navController,
        )
        if (showDialog) {
            MyDialog(
                onDismissRequest = { showDialog = false },
                onConfirmation = {
                    showDialog = false
                },
                dialogTitle = stringResource(id = R.string.dialog_title),
                dialogText = stringResource(id = R.string.dialog_libraries_message),
                icon = Icons.Default.Info
            )
        }
    }
}

@Composable
fun updateTopBar(navController: NavHostController): TopBarInfo {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreenRoutes =
        when (backStackEntry?.destination?.route) {
            ScreenRoutes.Home.route -> ScreenRoutes.Home
            ScreenRoutes.PokemonDetails.routeWithArgs -> ScreenRoutes.PokemonDetails
            else -> ScreenRoutes.Home
        }
    return when (currentScreenRoutes) {
        ScreenRoutes.Home ->
            TopBarInfo(
                title = stringResource(id = currentScreenRoutes.title),
                showBackIcon = false,
                showShareIcon = true,
                isVisible = true,
            )
        ScreenRoutes.PokemonDetails ->
            TopBarInfo(
                title = stringResource(id = currentScreenRoutes.title),
                showBackIcon = true,
                showShareIcon = false,
                isVisible = true,
            )
    }
}

data class TopBarInfo(
    val title: String,
    val showBackIcon: Boolean,
    val showShareIcon: Boolean,
    val isVisible: Boolean,
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}
