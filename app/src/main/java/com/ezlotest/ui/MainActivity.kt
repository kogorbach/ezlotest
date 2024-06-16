package com.ezlotest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ezlotest.ui.common.MainTopBar
import com.ezlotest.ui.detail.DetailScreen
import com.ezlotest.ui.detail.DetailScreenComposable
import com.ezlotest.ui.main.MainScreen
import com.ezlotest.ui.main.MainScreenComposable
import com.ezlotest.ui.theme.EzloTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        hideStatusBar()
        setContent {
            EzloTestTheme {
                val navController = rememberNavController()
                val viewModel: MainViewModel by viewModels()
                Scaffold(
                    topBar = { MainTopBar() },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavHost(navController = navController, startDestination = MainScreen) {
                        composable<MainScreen> {
                            MainScreenComposable(
                                viewModel = viewModel,
                                modifier = Modifier.padding(innerPadding),
                                onDeviceClick = { serialNumber ->
                                    navController.navigate(DetailScreen(deviceId = serialNumber))
                                })
                        }
                        composable<DetailScreen> {
                            val args = it.toRoute<DetailScreen>()
                            DetailScreenComposable(
                                viewModel = viewModel,
                                modifier = Modifier.padding(innerPadding),
                                deviceId = args.deviceId
                            )
                        }
                    }
                }
            }
        }
    }

    private fun hideStatusBar() {
        WindowCompat.getInsetsController(window, window.decorView).run {
            systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

            hide(WindowInsetsCompat.Type.systemBars())
        }
    }
}