package com.ibrahimcanerdogan.jetmarvelcomicslibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.navigation.BottomNavigation
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.navigation.Navigation
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.theme.JetMarvelComicsLibraryTheme
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.viewmodel.LibraryViewModel
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.viewmodel.CharacterListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val characterListViewModel: CharacterListViewModel by viewModels()
    private val libraryViewModel: LibraryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetMarvelComicsLibraryTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val navController = rememberNavController()

                Scaffold(
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    bottomBar = { BottomNavigation(navController = navController) }
                ) { paddingValues ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Navigation(navController = navController, characterListViewModel, libraryViewModel)
                    }
                }
            }
        }
    }
}