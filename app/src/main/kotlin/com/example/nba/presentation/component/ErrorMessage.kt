package com.example.nba.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nba.R

/**
 * A Composable function to display an error message with a retry button.
 *
 * @param message The error message to be displayed.
 * @param modifier Modifier for adjusting the appearance and behavior of this composable.
 * @param onClickRetry The action to be performed when the retry button is clicked.
 */
@Composable
fun ErrorMessage(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit,
) {
    Row(
        modifier = modifier.padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.weight(1f),
            maxLines = 2,
        )
        OutlinedButton(onClick = onClickRetry) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

/**
 * Preview for ErrorMessage composable function.
 */
@Preview(showBackground = true)
@Composable
fun ErrorMessagePreview() {
    ErrorMessage(message = "Error message", onClickRetry = {})
}
