package com.example.nba.presentation.view.player.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.nba.domain.entity.PlayerEntity
import com.example.nba.presentation.component.ErrorMessage
import com.example.nba.presentation.component.LoadingNextPageItem
import com.example.nba.presentation.component.PageLoader
import com.example.nba.presentation.component.PlayerListItem
import com.example.nba.presentation.navigation.NbaScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlayersScreen(
    viewModel: PlayersViewModel = koinViewModel(),
    navController: NavController,
) {
    val playerPagingItems: LazyPagingItems<PlayerEntity> =
        viewModel.playersState.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier,
    ) {
        item { Spacer(modifier = Modifier.padding(4.dp)) }
        items(playerPagingItems.itemCount) { index ->
            val player = playerPagingItems[index] as PlayerEntity
            PlayerListItem(
                player = player,
                onClick = {
                    navController.navigate(NbaScreen.Player.name + "/${player.id}")
                },
            )
        }
        playerPagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { PageLoader(modifier = Modifier.fillParentMaxSize()) }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = playerPagingItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier.fillParentMaxSize(),
                            message = error.error.localizedMessage ?: "Unknown error",
                            onClickRetry = { retry() },
                        )
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item { LoadingNextPageItem(modifier = Modifier) }
                }

                loadState.append is LoadState.Error -> {
                    val error = playerPagingItems.loadState.append as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier,
                            message = error.error.localizedMessage ?: "Unknown error",
                            onClickRetry = { retry() },
                        )
                    }
                }
            }
        }
        item { Spacer(modifier = Modifier.padding(4.dp)) }
    }
}
