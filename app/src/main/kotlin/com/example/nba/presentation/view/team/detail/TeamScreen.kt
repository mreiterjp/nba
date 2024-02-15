package com.example.nba.presentation.view.team.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.nba.presentation.component.PageLoader
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
    val team = viewModel.teamState.collectAsStateWithLifecycle().value
    Column(
        modifier = Modifier,
    ) {
        if (team != null) {
            // Display the team's detail item
            TeamDetailItem(
                team,
                // No action needed yet, when the team is clicked
                {},
            )
        } else {
            PageLoader(modifier = Modifier.fillMaxSize())
        }
    }
}
