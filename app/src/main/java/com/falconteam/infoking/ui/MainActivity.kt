package com.falconteam.infoking.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.falconteam.infoking.R
import com.falconteam.infoking.ui.navigation.graphs.RootNavGraph
import com.falconteam.infoking.ui.theme.InfoKingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = getColor(R.color.ic_application_background)
        }
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        super.onCreate(savedInstanceState)
        setContent {
            InfoKingTheme {
                isSystemInDarkTheme()
                RootNavGraph(navController = rememberNavController())
            }
        }
    }
}