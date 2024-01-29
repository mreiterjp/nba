package com.example.nba.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
fun PlayerListItem(
    player: PlayerEntity,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding(8.dp),

            ) {
            GlideImage(
                model = player.avatarUrl,
                contentDescription = player.firstName + " " + player.lastName,
                loading = placeholder(R.drawable.ic_avatar_placeholder),
                failure = placeholder(R.drawable.ic_avatar_placeholder),
                transition = CrossFade,
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "${player.firstName} ${player.lastName}",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.Black
                    ),
                )
                Text(
                    text = "Position: ${player.position}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Black
                    )
                )
                Text(
                    text = "Team: ${player.teamEntity?.name?:""}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Black
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewPlayerListItem() {
    PlayerListItem(player = PlayerEntity.generateFakePlayerEntity(),
        onClick = {}
    )
}

