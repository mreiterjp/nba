package com.example.nba.presentation.view.player.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.example.nba.presentation.component.PlayerDetailItem
import com.example.nba.presentation.navigation.NbaScreen

/**
 * A composable function that displays the detail screen for a player.
 *
 * @param navController The navigation controller to use for navigating to other screens.
 * @param viewModel The view model that manages the player's data.
 */
@Composable
fun PlayerScreen(
    navController: NavController,
    viewModel: PlayerViewModel,
) {
    // Extract the player from the view model's state flow
    val player = viewModel.playerState.collectAsState().value

    // Display the player's detail item
    PlayerDetailItem(
        player,
        // Navigate to the team screen when the player's team is clicked
        { navController.navigate(NbaScreen.Team.name + "/${player.teamEntity?.id ?: -1}") },
    )
}
