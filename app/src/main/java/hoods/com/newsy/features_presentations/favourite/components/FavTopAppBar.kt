package hoods.com.newsy.features_presentations.favourite.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import hoods.com.newsy.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavTopAppBar(
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit,
    topAppBarState: TopAppBarState = rememberTopAppBarState(),
    scrollBehavior: TopAppBarScrollBehavior? =
        TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Favourite",
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(openDrawer) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_newsy_logo),
                    contentDescription = "navigation",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}