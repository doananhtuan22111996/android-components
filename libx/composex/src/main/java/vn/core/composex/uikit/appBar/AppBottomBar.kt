package vn.core.composex.uikit.appBar

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import vn.core.libx.composex.R

@Composable
fun AppBottomNavigationBar(items: List<NavElement>) {
    NavigationBar(containerColor = Color.Transparent) {
        for (element in items) {
            NavigationBarItem(
                selected = element.selected,
                onClick = element.onClick,
                icon = element.icon,
                label = element.label,
            )
        }
    }
}

@Composable
fun AppNavigationRailBar(items: List<NavElement>) {
    NavigationRail {
        for (element in items) {
            NavigationRailItem(
                selected = element.selected,
                onClick = element.onClick,
                icon = element.icon,
                label = element.label,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationBarPreview() {
    Column {
        AppBottomNavigationBar(
            items = listOf(
                NavElement(
                    icon = {
                        Icon(Icons.Filled.Home, contentDescription = stringResource(R.string.icon))
                    },
                    label = {
                        Text(stringResource(R.string.item1))
                    },
                    onClick = {},
                ),
                NavElement(
                    icon = {
                        Icon(
                            Icons.Filled.AccountCircle,
                            contentDescription = stringResource(R.string.icon),
                        )
                    },
                    label = {
                        Text(stringResource(R.string.item2))
                    },
                    onClick = {},
                ),
            ),
        )
        AppNavigationRailBar(
            items = listOf(
                NavElement(
                    icon = {
                        Icon(Icons.Filled.Home, contentDescription = stringResource(R.string.icon))
                    },
                    label = {
                        Text(stringResource(R.string.item1))
                    },
                    onClick = {},
                ),
                NavElement(
                    icon = {
                        Icon(
                            Icons.Filled.AccountCircle,
                            contentDescription = stringResource(R.string.icon),
                        )
                    },
                    label = {
                        Text(stringResource(R.string.item2))
                    },
                    onClick = {},
                ),
            ),
        )
    }
}
