package com.example.nba.presentation.view.team.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.example.nba.presentation.component.TeamDetailItem

/**
 * A composable function that displays the detail screen for a team.
 *
 * @param navController The navigation controller to use for navigating to other screens.
 * @param viewModel The view model that manages the team's data.
 */
@Composable
fun TeamScreen(
    navController: NavController,
    viewModel: TeamViewModel,
) {
    // Extract the team from the view model's state flow
    val team = viewModel.teamState.collectAsState().value

    // Display the team's detail item
    TeamDetailItem(
        team,
        // No action needed when the team is clicked
        {},
    )
}
