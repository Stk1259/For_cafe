package com.example.forcafe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import com.example.forcafe.ui.theme.AppTheme
import com.example.forcafe.ui.theme.ForCafeTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import com.example.forcafe.screens.ApplicationScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForCafeTheme {
                val systemUiController = rememberSystemUiController()
                val primaryBackground = AppTheme.colors.primaryBackground
                 SideEffect {
                     systemUiController.setSystemBarsColor(
                         color = primaryBackground,
                         darkIcons = true
                     )
                 }
                ApplicationScreen()

                }
            }
        }
    }


