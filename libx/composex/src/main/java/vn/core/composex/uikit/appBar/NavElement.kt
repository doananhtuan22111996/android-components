package vn.core.composex.uikit.appBar

import androidx.compose.runtime.Composable

data class NavElement(
    val icon: @Composable () -> Unit,
    val label: @Composable () -> Unit,
    val selected: Boolean = false,
    val onClick: () -> Unit,
)
