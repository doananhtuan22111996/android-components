package vn.core.composex.uikit.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import vn.core.libx.composex.R

@Composable
fun IconBackButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            Icons.Filled.ArrowBackIosNew, contentDescription = stringResource(R.string.icon_back)
        )
    }
}