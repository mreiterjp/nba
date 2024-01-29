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

@Composable
fun TeamDetailItem(
    team: TeamEntity,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(8.dp),
        ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Team: ${team.fullName} (${team.abbreviation})",
                style = MaterialTheme.typography.subtitle1,
            )
            Text(
                text = "City: ${team.city}",
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = "${team.conference}/${team.division}",
                style = MaterialTheme.typography.subtitle2
            )
        }

    }
}

@Preview
@Composable
fun PreviewTeamDetailItem() {
    TeamDetailItem(team = TeamEntity.generateFakeTeamEntity(1),
        onClick = {}
    )
}

