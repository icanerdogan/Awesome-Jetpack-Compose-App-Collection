package com.ibrahimcanerdogan.notepadapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ibrahimcanerdogan.notepadapp.ui.navigation.MainNavigation
import com.ibrahimcanerdogan.notepadapp.ui.theme.NotepadAppDarkTheme
import com.ibrahimcanerdogan.notepadapp.ui.theme.NotepadAppLightTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isDarkTheme by remember { mutableStateOf(false) }

            MainContent(isDarkTheme = isDarkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MainNavigation(isDarkTheme = isDarkTheme) {
                        isDarkTheme = !isDarkTheme
                    }
                }
            }
        }
    }
}

@Composable
fun MainContent(isDarkTheme: Boolean, onToggleTheme: @Composable () -> Unit) {
    if (isDarkTheme) NotepadAppDarkTheme { onToggleTheme.invoke() }
    else NotepadAppLightTheme { onToggleTheme.invoke() }
}