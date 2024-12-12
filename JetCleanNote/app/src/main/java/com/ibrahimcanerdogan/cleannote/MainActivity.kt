package com.ibrahimcanerdogan.cleannote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ibrahimcanerdogan.cleannote.ui.navigation.NoteNavigation
import com.ibrahimcanerdogan.cleannote.ui.theme.CleanNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CleanNoteAppTheme {
                Surface {
                    NoteNavigation()
                }
            }
        }
    }
}
