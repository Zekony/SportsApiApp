package com.example.sportsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.sportsapp.presentation.core.SplashScreen
import com.example.sportsapp.presentation.core.SportsAppNavController
import com.example.sportsapp.ui.theme.SportsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            SportsAppTheme {
                val showSplash = rememberSaveable {
                    mutableStateOf(true)
                }
                LaunchedEffect(showSplash) {
                    delay(2000)
                    showSplash.value = false
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    if (showSplash.value)
                        SplashScreen()
                    else
                        SportsAppNavController()
                }
            }
        }
    }
}



