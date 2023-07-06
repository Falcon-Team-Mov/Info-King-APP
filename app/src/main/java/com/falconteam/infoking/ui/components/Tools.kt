package com.falconteam.infoking.ui.components

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.round
import kotlin.random.Random

@Composable
fun TextResponsiveSize(size: TextUnit): TextUnit {

    val configuration = LocalConfiguration.current

    val scaleFactor = if (configuration.smallestScreenWidthDp >= 600) 1.2f else 0.7f

    return (size * scaleFactor)
}

@Composable
fun ElementResponsiveSize(size: Dp): Dp {

    val configuration = LocalConfiguration.current

    val scaleFactor = if (configuration.smallestScreenWidthDp >= 600) 1.2f else 0.7f

    return (size * scaleFactor)
}

suspend fun generateRandomNumber(max: Int, min: Int = 1): Int {
    return withContext(Dispatchers.Default) {
        val randomNumber = Random.nextInt(min, max - 1)
        randomNumber
    }
}

fun openPlayStore(context: Context) {
    val packageName = context.packageName
    val playStoreUri = Uri.parse("market://details?id=$packageName")
    val intent = Intent(Intent.ACTION_VIEW, playStoreUri)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun getCurrentDateTime(): String {
    val currentDateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return currentDateTime.format(formatter)
}

suspend fun attackgenerator(attack: Int, defensa: Int): Float {
    if (attack <= 0 || defensa <= 0) {
        return 0f
    } else {
        val random = (generateRandomNumber(defensa, defensa/2))
        val _attack = (attack * random / (defensa * 1f))
        Log.d("Prueba", "attackgenerator: $_attack, random: $random, defensa: $defensa")
        return if (_attack <= 0) {
            0f
        } else {
            _attack.toFloat()
        }
    }
}