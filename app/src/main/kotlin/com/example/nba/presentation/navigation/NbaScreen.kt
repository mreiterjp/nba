package com.example.nba.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nba.R
import com.example.nba.presentation.component.NbaAppBar
import com.example.nba.presentation.view.player.detail.PlayerScreen
import com.example.nba.presentation.view.player.detail.PlayerViewModel
import com.example.nba.presentation.view.player.list.PlayersScreen
import com.example.nba.presentation.view.team.detail.TeamScreen
import com.example.nba.presentation.view.team.detail.TeamViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

/**
 * enum values that represent the screens in the app
 */
enum class NbaScreen(
    @StringRes val title: Int,
    val route: String,
) {
    Player(title = R.string.player_detail, route = "player/{playerId}"),
    Players(title = R.string.players_list, route = "players"),
    Team(title = R.string.team_detail, route = "team/{teamId}"),
    ;

    companion object {
        fun findNbaScreenFromRoute(route: String): NbaScreen {
            return when (route) {
                "player/{playerId}" -> Player
                "players" -> Players
                "team/{teamId}" -> Team
                else -> Players
            }
        }
    }
}

@Composable
fun NbaApp(navController: NavHostController = rememberNavController()) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen =
        NbaScreen.findNbaScreenFromRoute(
            backStackEntry?.destination?.route ?: NbaScreen.Players.route,
        )

    Scaffold(
        topBar = {
            NbaAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
            )
        },
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = NbaScreen.Players.route,
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            composable(route = NbaScreen.Players.route) {
                PlayersScreen(navController = navController)
            }
            composable(
                route = NbaScreen.Player.route,
                arguments = listOf(navArgument("playerId") { type = NavType.IntType }),
            ) { backStackEntry ->
                val playerId = backStackEntry.arguments?.getInt("playerId") ?: -1
                PlayerScreen(
                    navController = navController,
                    viewModel =
                        koinViewModel<PlayerViewModel>(
                            parameters = { parametersOf(playerId) },
                        ),
                )
            }
            composable(
                route = NbaScreen.Team.route,
                arguments = listOf(navArgument("teamId") { type = NavType.IntType }),
            ) { backStackEntry ->
                val teamId = backStackEntry.arguments?.getInt("teamId")
                TeamScreen(
                    navController = navController,
                    viewModel =
                        koinViewModel<TeamViewModel>(
                            parameters = { parametersOf(teamId) },
                        ),
                )
            }
        }
    }
}
