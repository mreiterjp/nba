package com.example.nba.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nba.domain.entity.TeamEntity

/**
 * A composable function that displays a detail item for a team.
 *
 * @param team The team to display.
 * @param onClick A callback function to execute when the team's item is clicked.
 */
@Composable
fun TeamDetailItem(
    team: TeamEntity,
    onClick: () -> Unit,
) {
    // Create a full-width box to wrap the team's information
    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(8.dp),
    ) {
        // Create a centered column to arrange the team's information
        Column(
            modifier =
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            // Display the team's full name and abbreviation
            Text(
                text = "Team: ${team.fullName} (${team.abbreviation})",
                style = MaterialTheme.typography.subtitle1,
            )

            // Display the team's city
            Text(
                text = "City: ${team.city}",
                style = MaterialTheme.typography.subtitle2,
            )

            // Display the team's conference and division
            Text(
                text = "${team.conference}/${team.division}",
                style = MaterialTheme.typography.subtitle2,
            )
        }
    }
}

@Preview
@Composable
fun PreviewTeamDetailItem() {
    TeamDetailItem(
        team = TeamEntity.generateFakeTeamEntity(1),
        onClick = {},
    )
}
