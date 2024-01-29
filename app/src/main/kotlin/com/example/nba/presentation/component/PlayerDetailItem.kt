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

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlayerDetailItem(
    player: PlayerEntity,
    onClick: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(8.dp),
    ) {
        Text(
            text = player.firstName + " " + player.lastName,
            style = MaterialTheme.typography.displaySmall,
        )
        Row(
            // verticalAlignment = Alignment.CenterVertically,
            modifier =
                Modifier
                    .fillMaxWidth(),
        ) {
            GlideImage(
                model = player.avatarUrl,
                contentDescription = player.firstName + " " + player.lastName,
                loading = placeholder(R.drawable.ic_avatar_placeholder),
                failure = placeholder(R.drawable.ic_avatar_placeholder),
                transition = CrossFade,
                modifier = Modifier.size(60.dp),
            )
            Column(modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp)) {
                Text(
                    text = "Position: ${player.position}",
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = "Height: ${player.heightFeet}' ${player.heightInches}\"",
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = "Weight: ${player.weightPounds} lbs",
                    style = MaterialTheme.typography.bodyLarge,
                )
                if (player.teamEntity != null) {
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
