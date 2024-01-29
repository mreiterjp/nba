package com.example.nba.presentation.view.player.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.nba.domain.entity.PlayerEntity
import com.example.nba.presentation.component.ErrorMessage
import com.example.nba.presentation.component.LoadingNextPageItem
import com.example.nba.presentation.component.PageLoader
import com.example.nba.presentation.component.PlayerListItem
import com.example.nba.presentation.navigation.NbaScreen
import org.koin.androidx.compose.koinViewModel

/**
 * A composable function that displays a list of players.
 *
 * @param viewModel The view model that manages the player's data.
 * @param navController The navigation controller to use for navigating to other screens.
 */
@Composable
fun PlayersScreen(
    viewModel: PlayersViewModel = koinViewModel(),
    navController: NavController,
) {
    // Collect the player list from the view model's state flow as a lazy paging item
    val playerPagingItems = viewModel.playersState.collectAsLazyPagingItems()

    // Create a lazy column to display the player list
    LazyColumn(
        modifier = Modifier,
    ) {
        // Add a spacer at the top of the list
        item { Spacer(modifier = Modifier.padding(4.dp)) }

        // Loop over the player items in the list
        items(playerPagingItems.itemCount) { index ->
            // Get the player at the current index
            val player = playerPagingItems[index] as PlayerEntity

            // Display the player as a list item
            PlayerListItem(
                player = player,
                onClick = {
                    // Navigate to the player detail screen when a player is clicked
                    navController.navigate(NbaScreen.Player.name + "/${player.id}")
                },
            )
        }

        // Handle loading and error states for the player list
        playerPagingItems.apply {
            when {
                // Display a loading indicator when the refresh load state is loading
                loadState.refresh is LoadState.Loading -> {
                    item { PageLoader(modifier = Modifier.fillParentMaxSize()) }
                }

                // Display an error message when the refresh load state is error
                loadState.refresh is LoadState.Error -> {
                    val error = playerPagingItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier.fillParentMaxSize(),
                            message = error.error.localizedMessage ?: "Unknown error",
                            onClickRetry = { retry() },
                        )
                    }
                }

                // Display a loading indicator when the append load state is loading
                loadState.append is LoadState.Loading -> {
                    item { LoadingNextPageItem(modifier = Modifier) }
                }

                // Display an error message when the append load state is error
                loadState.append is LoadState.Error -> {
                    val error = playerPagingItems.loadState.append as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier,
                            message = error.error.localizedMessage ?: "Unknown error",
                            onClickRetry = { retry() },
                        )
                    }
                }
            }
        }

        // Add a spacer at the bottom of the list
        item { Spacer(modifier = Modifier.padding(4.dp)) }
    }
}
