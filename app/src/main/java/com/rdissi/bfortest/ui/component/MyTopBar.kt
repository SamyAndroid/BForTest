package com.rdissi.bfortest.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.rdissi.bfortest.ui.theme.Blue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    canNavigateBack: Boolean,
    showActionIcons: Boolean,
    navigateUp: () -> Unit = {},
    onShareClick: () -> Unit = {},
    onInfoClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White,
            )
        },
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Blue),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(
                    onClick = navigateUp,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.White,
                    )
                }
            }
        },
        actions = {
            if (showActionIcons) {
                IconButton(
                    onClick = onShareClick,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Share,
                        contentDescription = null,
                        tint = Color.White,
                    )
                }
                IconButton(
                    onClick = onInfoClick,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = null,
                        tint = Color.White,
                    )
                }
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun MyTopBarHomePreview() {
    MyTopAppBar(
        title = "Title",
        canNavigateBack = false,
        showActionIcons = false,
        onShareClick = {},
    )
}

@Preview(showBackground = true)
@Composable
fun MyTopBarFullPreview() {
    MyTopAppBar(
        title = "Title",
        canNavigateBack = true,
        showActionIcons = true,
        onShareClick = {},
    )
}
