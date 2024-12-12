package com.ibrahimcanerdogan.jetmovielibraryapp.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.navigation.MovieScreens
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.theme.fontFamily
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.viewmodel.home.MovieHomeState
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.widget.MovieListItem
import kotlinx.coroutines.launch

@Composable
fun MovieSearchBar(
    modifier: Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var keyboardController = LocalSoftwareKeyboardController.current
    var searchQuery by remember { mutableStateOf("") }
    var isHintDisplayed by remember { mutableStateOf(hint != "") }

    Box(modifier = modifier) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                if (searchQuery.length > 3) {
                    onSearch(searchQuery)
                }
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 18.sp,
                fontFamily = fontFamily,
                color = MaterialTheme.colorScheme.inverseSurface
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .border(2.dp, MaterialTheme.colorScheme.secondary, RoundedCornerShape(10.dp))
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true && searchQuery.isEmpty()
                },
            keyboardActions = KeyboardActions(onDone = {
                if (searchQuery.length > 3) {
                    onSearch(searchQuery)
                    keyboardController?.hide()
                }
            })
        )

        if (isHintDisplayed) {
            Text(
                text = hint,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
                    .padding(horizontal = 10.dp),
                fontSize = 18.sp,
                fontFamily = fontFamily,
                color = MaterialTheme.colorScheme.inverseSurface
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieHorizontalPagerView(
    state: MovieHomeState,
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState { state.stateMovieList.size }

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fill,
            key = {
                state.stateMovieList[it].movieSearchImdbID
            }
        ) {
            MovieListItem(
                modifier = Modifier.padding(vertical = 20.dp, horizontal = 15.dp),
                movieListData = state.stateMovieList[it],
                onItemClick = {
                    navController.navigate(MovieScreens.DETAIL_SCREEN.name + "/{${it.movieSearchImdbID}}")
                }
            )
        }

        Box(
            modifier = Modifier
                .offset(y = -(16).dp)
                .fillMaxWidth(0.95f)
                .height(60.dp)
                .clip(RoundedCornerShape(100))
                .background(Color.Transparent)
                .padding(8.dp)
                .align(Alignment.Center)
        ) {
            if (pagerState.currentPage != 0) {
                IconButton(
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(
                                pagerState.currentPage - 1
                            )
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clip(CircleShape)
                        .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape)
                        .background(Color.White)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                        tint = Color.Black,
                        contentDescription = "Go Before Pager"
                    )
                }
            }
            if (pagerState.currentPage != state.stateMovieList.size - 1) {
                IconButton(
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(
                                pagerState.currentPage + 1
                            )
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clip(CircleShape)
                        .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape)
                        .background(Color.White)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                        tint = Color.Black,
                        contentDescription = "Go Next Pager"
                    )
                }
            }
        }
    }
}