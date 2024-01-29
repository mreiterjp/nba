package com.example.nba.presentation.view.team.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.example.nba.presentation.component.TeamDetailItem

@Composable
fun TeamScreen(
    navController: NavController,
    viewModel: TeamViewModel,
) {
    val teamItem = viewModel.teamState.collectAsState().value
    TeamDetailItem(teamItem, { })
}
