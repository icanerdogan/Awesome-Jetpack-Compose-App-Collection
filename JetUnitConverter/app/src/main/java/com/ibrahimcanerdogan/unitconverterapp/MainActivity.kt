package com.ibrahimcanerdogan.unitconverterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ibrahimcanerdogan.unitconverterapp.ui.screen.BaseScreen
import com.ibrahimcanerdogan.unitconverterapp.ui.theme.Color1Beige
import com.ibrahimcanerdogan.unitconverterapp.ui.theme.UnitConverterAppTheme
import com.ibrahimcanerdogan.unitconverterapp.ui.viewmodel.ConverterViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var factory: ConverterViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color1Beige
                ) {
                    BaseScreen(factory = factory)
                }
            }
        }
    }
}