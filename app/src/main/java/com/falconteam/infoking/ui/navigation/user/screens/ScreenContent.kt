package com.falconteam.infoking.ui.navigation.user.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.falconteam.infoking.ui.navigation.graphs.Graph
import com.falconteam.infoking.ui.theme.Typography

@Composable
fun ScreenContent(name: String, onClick: () -> Unit, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.clickable { onClick() },
            text = name,
            style = Typography.bodyLarge,
            color = Color.White
        )   
//        Button(
//          modifier = Modifier,
//            onClick = {
//                try {
//                    navController.navigate(Graph.AUTH)
//                } catch (e: Exception) {
//                    Log.d("NAV", e.message.toString())
//                }
//            }
//        ) {
//            Text(text = "Pruebita")
//        }
    }
}