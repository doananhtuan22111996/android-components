package vn.core.composex.uikit.paging

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import vn.core.domain.BaseModel
import vn.core.libx.composex.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun <T : BaseModel> Paging(
    lazyPagingItems: LazyPagingItems<T>,
    lazyListState: LazyListState = rememberLazyListState(),
    items: @Composable (index: Int) -> Unit = {},
    footer: @Composable (() -> Unit)? = null,
    onRetry: (() -> Unit)? = null,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val isInitialLoading =
        lazyPagingItems.itemCount == 0 && lazyPagingItems.loadState.refresh is LoadState.Loading
    val isInitialError =
        lazyPagingItems.itemCount == 0 && lazyPagingItems.loadState.refresh is LoadState.Error
    val isPagingFooter =
        lazyPagingItems.loadState.append is LoadState.Loading || lazyPagingItems.loadState.append is LoadState.Error

    var isRefreshing by remember { mutableStateOf(false) }
    val pullToRefreshState = rememberPullToRefreshState()

    // Initial no need to refresh
    isRefreshing =
        lazyPagingItems.loadState.refresh is LoadState.Loading && lazyPagingItems.itemCount > 0

    Box(modifier = Modifier.fillMaxSize()) {

        if (isInitialLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            return@Box
        }

        if (isInitialError) {
            val message = (lazyPagingItems.loadState.refresh as LoadState.Error).error.message
                ?: stringResource(
                    id = R.string.unknown_error
                )
            Box(modifier = Modifier.align(Alignment.Center)) {
                PagingRetry(message = message, onRetry = onRetry ?: { lazyPagingItems.retry() })
            }
            return@Box
        }

        PullToRefreshBox(modifier = Modifier.align(Alignment.TopCenter),
            state = pullToRefreshState,
            isRefreshing = isRefreshing,
            onRefresh = {
                isRefreshing = true
                lazyPagingItems.refresh()
            }) {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = contentPadding
            ) {
                items(count = lazyPagingItems.itemCount,
                    key = lazyPagingItems.itemKey { it.hashCode() }) { index ->
                    Box(modifier = Modifier.animateItemPlacement()) {
                        items(index)
                    }
                }
                if (isPagingFooter) {
                    footer ?: item {
                        Box(modifier = Modifier.animateItemPlacement()) {
                            PagingFooter(loadState = lazyPagingItems.loadState.append,
                                onRetry = onRetry ?: {
                                    lazyPagingItems.retry()
                                })
                        }
                    }
                }
            }
        }
    }
}