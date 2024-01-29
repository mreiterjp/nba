package com.example.nba.presentation.navigation

import androidx.annotation.StringRes
import com.example.nba.R

/**
 * An enum class that represents the screens in the NBA application.
 *
 * Each enum value represents a different screen in the application, with a corresponding title and route.
 */
enum class NbaScreen(
    @StringRes val title: Int, // The string resource ID for the title of the screen
    val route: String, // The route pattern for the screen
) {
    /**
     * Represents the player detail screen.
     */
    Player(title = R.string.player_detail, route = "player/{playerId}"),

    /**
     * Represents the players list screen.
     */
    Players(title = R.string.players_list, route = "players"),

    /**
     * Represents the team detail screen.
     */
    Team(title = R.string.team_detail, route = "team/{teamId}"),
    ;

    companion object {
        /**
         * Finds the `NbaScreen` value corresponding to the given route.
         *
         * @param route The route to match.
         * @return The corresponding `NbaScreen` value, or `Players` if no match is found.
         */
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
