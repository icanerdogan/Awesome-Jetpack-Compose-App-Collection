package com.ibrahimcanerdogan.jettodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ibrahimcanerdogan.jettodo.ui.navigation.ToDoNavigation
import com.ibrahimcanerdogan.jettodo.ui.theme.JetToDoTheme
import com.ibrahimcanerdogan.jettodo.ui.viewmodel.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val viewModel: BaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        // enableEdgeToEdge()
        setContent {
            JetToDoTheme {
                navController = rememberNavController()
                ToDoNavigation(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}