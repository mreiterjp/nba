package com.example.nba.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nba.R

@Composable
fun PageLoader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.loading_data),
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        CircularProgressIndicator(Modifier.padding(top = 10.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PageLoaderPreview() {
    PageLoader(modifier = Modifier.padding(32.dp))
}

@Composable
fun LoadingNextPageItem(modifier: Modifier) {
    CircularProgressIndicator(
        modifier =
        modifier
            .fillMaxWidth()
            .padding(10.dp)
            .wrapContentWidth(Alignment.CenterHorizontally),
    )
}

@Preview(showBackground = true)
@Composable
fun LoadingNextPageItemPreview() {
    LoadingNextPageItem(Modifier.padding(32.dp))
}
