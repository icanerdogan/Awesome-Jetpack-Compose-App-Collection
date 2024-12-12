package com.ibrahimcanerdogan.jetbankdatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.ibrahimcanerdogan.jetbankdatabase.common.network.NetworkStatusChecker
import com.ibrahimcanerdogan.jetbankdatabase.ui.component.NoInternetDialog
import com.ibrahimcanerdogan.jetbankdatabase.ui.navigation.AppNavigation
import com.ibrahimcanerdogan.jetbankdatabase.ui.theme.JetBankDatabaseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val internetFlow = NetworkStatusChecker.networkChecker(context)
            NoInternetDialog(internetFlow)

            JetBankDatabaseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier.fillMaxSize().padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val navController = rememberNavController()
                        AppNavigation(navController)
                    }
                }
            }
        }
    }
}