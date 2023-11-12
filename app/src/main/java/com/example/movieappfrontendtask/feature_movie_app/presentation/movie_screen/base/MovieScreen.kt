package com.example.movieappfrontendtask.feature_movie_app.presentation.movie_screen.base

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieappfrontendtask.core.presentation.ui.theme.textColor
import com.example.movieappfrontendtask.feature_movie_app.domain.model.movie.MovieResult
import com.example.movieappfrontendtask.feature_movie_app.presentation.destinations.MovieDetailScreenDestination
import com.example.movieappfrontendtask.feature_movie_app.presentation.movie_screen.MovieScreenEvent
import com.example.movieappfrontendtask.feature_movie_app.presentation.movie_screen.MovieScreenState
import com.example.movieappfrontendtask.feature_movie_app.presentation.movie_screen.MovieScreenViewModel
import com.example.movieappfrontendtask.feature_movie_app.presentation.movie_screen.component.BottomSheetContent
import com.example.movieappfrontendtask.feature_movie_app.presentation.movie_screen.component.MovieItem
import com.example.movieappfrontendtask.feature_movie_app.presentation.movie_screen.component.MovieScreenTopBar
import com.example.movieappfrontendtask.feature_movie_app.presentation.movie_screen.component.RetryContent
import com.example.movieappfrontendtask.feature_movie_app.presentation.movie_screen.component.SearchAppBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class,
)
@Composable
@RootNavGraph(start = true)
@Destination()
fun MovieScreen(
    navigate: DestinationsNavigator,
    viewModel: MovieScreenViewModel = hiltViewModel(),
) {
    val state = viewModel.state

    val isLoadingShimmer = state.isLoadingShimmer
    val isLoadingShimmerBottom = state.isLoadingShimmerBottom

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    val categories = listOf(
        "Now Playing", "Popular", "Top Rated", "Upcoming"
    )

    val pagerState = rememberPagerState {
        categories.size
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val coroutineScope = rememberCoroutineScope()

    val focusRequester = remember { FocusRequester() }

    val focusManager = LocalFocusManager.current

    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
        viewModel.onEvent(MovieScreenEvent.OnCategoryChange(category = categories[selectedTabIndex]))
    }


    LaunchedEffect(key1 = Unit) {
        if (state.searchQuery.isNotEmpty()) {
            viewModel.onEvent(MovieScreenEvent.OnSearchQueryChange(searchQuery = state.searchQuery))
        }
    }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var shouldBottomSheetShow by remember { mutableStateOf(false) }

    if (shouldBottomSheetShow) {

        ModalBottomSheet(
            onDismissRequest = {
                shouldBottomSheetShow = false
                viewModel.onEvent(MovieScreenEvent.OnButtomSheetContentHide)
            },
            sheetState = sheetState,
            content = {

                state.movieResult?.let { movieResult ->
                    viewModel.onEvent(MovieScreenEvent.ShowSimilarMovie(movieResult.movieId))

                    BottomSheetContent(
                        isLoading = isLoadingShimmerBottom,
                        state = state,
                        movieResult = movieResult,
                        onReadFullStoryButtonClicked = {
                            coroutineScope.launch {
                                sheetState.hide()
                                navigate.navigate(
                                    MovieDetailScreenDestination(movieResult.movieId)
                                )
                            }.invokeOnCompletion {
                                if (!sheetState.isVisible) shouldBottomSheetShow = false
                            }
                        }
                    )

                }

            }
        )
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Crossfade(targetState = state.isSearchBarVisible, label = "") { isVisible ->
            if (isVisible) {
                Column {
                    SearchAppBar(
                        modifier = Modifier.focusRequester(focusRequester),
                        value = state.searchQuery,
                        onValueChange = { newValue ->
                            viewModel.onEvent(MovieScreenEvent.OnSearchQueryChange(newValue))
                        },
                        onCloseIconClicked = { viewModel.onEvent(MovieScreenEvent.OnCloseSearchIconClick) },
                        onSearchClicked = {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        }
                    )
                    MovieList(
                        state = state,
                        onCardClick = { movie ->
                            shouldBottomSheetShow = true
                            viewModel.onEvent(MovieScreenEvent.OnMovieCardClick(movie = movie))
                        },
                        onRetry = {
                            viewModel.onEvent(MovieScreenEvent.OnSearchQueryChange(state.searchQuery))
                        },
                        isLoading = isLoadingShimmer
                    )
                }
            } else {
                Scaffold(
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = {
                        MovieScreenTopBar(
                            scrollBehavior = scrollBehavior,
                            onSearchIconClicked = {
                                viewModel.onEvent(MovieScreenEvent.OnSearchIconClicked)
                                coroutineScope.launch {
                                    delay(500)
                                    focusRequester.requestFocus()
                                }
                            }
                        )
                    }
                ) { padding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                    ) {

                        ScrollableTabRow(

                            selectedTabIndex = selectedTabIndex,
                            modifier = Modifier
                                .fillMaxWidth(),
                            backgroundColor = Color(0xff06164c),
                            edgePadding = 0.dp
                        ) {

                            categories.forEachIndexed { index, item ->

                                Tab(
                                    selected = index == selectedTabIndex,
                                    selectedContentColor = textColor,
                                    onClick = {
                                        coroutineScope.launch {
                                            selectedTabIndex = index
                                        }
                                    },
                                    text = {
                                        Text(
                                            text = item,
                                            modifier = Modifier.padding(
                                                vertical = 8.dp,
                                                horizontal = 2.dp
                                            ),
                                            color = Color(0xFFDBE8E1)
                                        )
                                    }
                                )
                            }

                        }


                        HorizontalPager(
                            state = pagerState
                        ) {
                            MovieList(
                                state = state,
                                onCardClick = { movie ->
                                    shouldBottomSheetShow = true
                                    viewModel.onEvent(
                                        MovieScreenEvent.OnMovieCardClick(
                                            movie = movie
                                        )
                                    )
                                },
                                onRetry = {
                                    viewModel.onEvent(MovieScreenEvent.OnCategoryChange(state.category))
                                },
                                isLoading = isLoadingShimmer
                            )
                        }
                    }
                }
            }
        }
    }


}


@Composable
fun MovieList(
    isLoading: Boolean,
    state: MovieScreenState,
    onCardClick: (MovieResult) -> Unit,
    onRetry: () -> Unit,
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        state.movie?.let { movie ->

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(10.dp),
            ) {

                items(movie.results.size) { i ->

                    val movieResult = movie.results[i]

                    if (i > 0) {
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    MovieItem(
                        moviesResult = movieResult,
                        modifier = Modifier
                            .fillMaxWidth(),
                        onCardClick = onCardClick,
                        isLoading = isLoading
                    )
                }

            }

        }

        if (state.isLoading) {
            CircularProgressIndicator(color = Color(0xFFDBE8E1))
        }
        if (state.error != null) {
            RetryContent(
                error = state.error,
                onRetry = onRetry
            )
        }
    }
}