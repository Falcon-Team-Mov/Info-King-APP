package com.falconteam.infoking.ui.navigation

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay

@Composable
fun PressBackAgainToExit() {
    val context = LocalContext.current

    var showToast by remember {
        mutableStateOf(false)
    }

    var backPressState by remember {
        mutableStateOf<BackPress>(BackPress.Idle)
    }

    if(showToast) {
        Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT).show()
        showToast = false
    }

    LaunchedEffect(key1 = backPressState) {
        if(backPressState == BackPress.FirstPress) {
            delay(2000) // wait 2 seconds until next change
            backPressState = BackPress.Idle //return to Idle state
        }
    }

    BackHandler(backPressState == BackPress.Idle) {
        backPressState = BackPress.FirstPress
        showToast = true
    }
}

sealed class BackPress {
    object Idle : BackPress()
    object FirstPress : BackPress()
}