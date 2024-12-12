package com.ibrahimcanerdogan.jetmovielibraryapp

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.theme.JetMovieLibraryAppTheme
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.navigation.MovieMainNavigation
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.navigation.MovieScreens
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.viewmodel.home.MovieHomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MovieHomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashIsLoading.value
            }
        }
        enableEdgeToEdge()
        setContent {
            JetMovieLibraryAppTheme {
                MainContent()
            }
        }
    }
}

@Preview
@Composable
private fun MainContent() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.secondary,
                actions = {
                    IconButton(
                        modifier = Modifier.padding(start = 5.dp),
                        onClick = {
                            navController.navigate(MovieScreens.LIST_SCREEN.name)
                        }
                    ) {
                        Icon(
                            modifier = Modifier.size(27.dp),
                            imageVector = Icons.Default.Home,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            contentDescription = "Home"
                        )
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { navController.navigate(MovieScreens.FAVORITE_SCREEN.name) },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(Icons.Outlined.Favorite, "BottomBar Favorite Icon")
                    }
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.surface
        ) {
            MovieMainNavigation(navController)
        }
    }
}