package vn.core.composex.uikit.alert

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import vn.core.composex.EMPTY_STRING
import vn.core.libx.composex.R

@Composable
fun AlertSuccessDialogComponent(
    title: String,
    message: String,
    onDismissRequest: (() -> Unit) = {},
) {
    AlertDialog(
        icon = {
            Icon(
                modifier = Modifier.size(96.dp),
                imageVector = Icons.Filled.Check,
                contentDescription = EMPTY_STRING,
            )
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        text = {
            Text(text = message, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = onDismissRequest) {
                Text(stringResource(R.string.accept))
            }
        },
    )
}
