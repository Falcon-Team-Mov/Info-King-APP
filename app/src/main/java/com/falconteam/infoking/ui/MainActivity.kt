package com.falconteam.infoking.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.navigation.compose.rememberNavController
import com.falconteam.infoking.ui.navigation.graphs.RootNavGraph
import com.falconteam.infoking.ui.theme.InfoKingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InfoKingTheme {
                isSystemInDarkTheme()
                RootNavGraph(navController = rememberNavController())
            }
        }
    }
}