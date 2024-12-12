package com.ibrahimcanerdogan.calculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ibrahimcanerdogan.calculatorapp.ui.screen.CalculatorScreen
import com.ibrahimcanerdogan.calculatorapp.ui.theme.CalculatorAppTheme
import com.ibrahimcanerdogan.calculatorapp.ui.viewmodel.CalculatorViewModel
import com.ibrahimcanerdogan.calculatorapp.util.StoreTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val scope = rememberCoroutineScope()
            val dataStore = StoreTheme(this)

            val themeInitialValue = dataStore.getThemeSynchronously()
            val darkTheme by dataStore.getTheme.collectAsState(initial = themeInitialValue)
            val viewModel by viewModels<CalculatorViewModel>()

            CalculatorAppTheme(darkTheme = darkTheme) {
                CalculatorScreen(
                    viewModel = viewModel,
                    darkTheme = darkTheme,
                    onThemeUpdated = {
                        scope.launch {
                            dataStore.saveTheme(!darkTheme)
                        }
                    }
                )
            }
        }
    }
}