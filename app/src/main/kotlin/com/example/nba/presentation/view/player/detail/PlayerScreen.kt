package com.example.nba.presentation.view.player.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.example.nba.presentation.component.PlayerDetailItem
import com.example.nba.presentation.navigation.NbaScreen

@Composable
fun PlayerScreen(
    navController: NavController,
    viewModel: PlayerViewModel
) {
    val player = viewModel.playerState.collectAsState().value
    PlayerDetailItem(player,
        { navController.navigate(NbaScreen.Team.name + "/${player.teamEntity?.id ?: -1}") }
    )
}


