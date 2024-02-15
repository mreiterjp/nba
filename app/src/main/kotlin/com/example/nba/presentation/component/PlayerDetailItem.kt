package com.example.nba.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.nba.R
import com.example.nba.domain.entity.PlayerEntity

/**
 * A composable function that displays a detailed item for a player.
 *
 * @param player The player to display.
 * @param onClick A callback function to execute when the player's item is clicked.
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlayerDetailItem(
    player: PlayerEntity,
    onClick: () -> Unit,
) {
    // Create a column layout to arrange the player's information
    Column(
        modifier =
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(8.dp),
    ) {
        // Display the player's full name
        Text(
            text = player.firstName + " " + player.lastName,
            style = MaterialTheme.typography.displaySmall,
        )

        // Display the player's avatar
        Row(
            // Align the avatar and additional information vertically
            modifier =
            Modifier
                .fillMaxWidth(),
        ) {
            // Display the player's avatar
            GlideImage(
                model = player.avatarUrl,
                contentDescription = player.firstName + " " + player.lastName,
                loading = placeholder(R.drawable.ic_avatar_placeholder),
                failure = placeholder(R.drawable.ic_avatar_placeholder),
                transition = CrossFade,
                modifier = Modifier.size(60.dp),
            )

            // Display additional information about the player, including position, height, weight, and team
            Column(modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp)) {
                // Display the player's position
                Text(
                    text = "Position: ${player.position}",
                    style = MaterialTheme.typography.bodyLarge,
                )

                // Display the player's height
                Text(
                    text = "Height: ${player.heightFeet}' ${player.heightInches}\"",
                    style = MaterialTheme.typography.bodyLarge,
                )

                // Display the player's weight
                Text(
                    text = "Weight: ${player.weightPounds} lbs",
                    style = MaterialTheme.typography.bodyLarge,
                )

                // Display the player's team name if it exists
                if (player.teamEntity != null) {
                    // Underline and color the team name to indicate it's a clickable link
                    Text(
                        text = "Team: ${player.teamEntity.name}",
                        style =
                        MaterialTheme.typography.bodyLarge.copy(
                            textDecoration = TextDecoration.Underline,
                            color = MaterialTheme.colorScheme.primary,
                        ),
                        modifier =
                        Modifier
                            .clickable {
                                onClick()
                            },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewPlayerDetailItem() {
    PlayerDetailItem(
        player = PlayerEntity.generateFakePlayerEntity(),
        onClick = {},
    )
}
