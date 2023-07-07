package com.falconteam.infoking.ui.components

import android.app.Application
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.falconteam.infoking.ui.navigation.graphs.RootNavGraph
import com.falconteam.infoking.ui.theme.InfoKingTheme
import com.falconteam.infoking.ui.viewmodels.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.text.DecimalFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

@Composable
fun TextResponsiveSize(size: TextUnit): TextUnit {

    val configuration = LocalConfiguration.current

    val scaleFactor = if (configuration.smallestScreenWidthDp >= 600) 1.2f else 0.6f

    return (size * scaleFactor)
}

@Composable
fun ElementResponsiveSize(size: Dp): Dp {

    val configuration = LocalConfiguration.current

    val scaleFactor = if (configuration.smallestScreenWidthDp >= 600) 1.2f else 0.7f

    return (size * scaleFactor)
}

@Composable
fun resizePopUp(size: Dp): Dp {
    val configuration = LocalConfiguration.current
    val scaleFactor = if (configuration.smallestScreenWidthDp >= 600) 1.5f else 0.1f
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
fun getCurrentDateTime(): String {
    val currentDateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return currentDateTime.format(formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
fun setLastTime(context: Context, value: Boolean) {
    val playing = runBlocking {
        getData(context, keyInt = PreferencesKeys.TIME_PLAYING, type = 2).toString().toInt()
    }
    val time = calculateTime(
        runBlocking { getData(context, PreferencesKeys.LAST_CONECTION).toString() },
        getCurrentDateTime()
    ).toInt()

    HealtTimer(context, time + playing)

    setData(
        context,
        dataBoolean = value,
        BooleanKey = PreferencesKeys.OPEN_GAME,
        type = 4
    )
}

fun HealtTimer(context: Context, seconds: Int, popup: Boolean = false): Boolean {
    var update = false
    val viewLogin =
        ViewModelProvider.AndroidViewModelFactory.getInstance(context.applicationContext as Application)
            .create(LoginViewModel::class.java)
    if (seconds >= 10 * 60) {
        val vida = runBlocking {
            getData(context, keyInt = PreferencesKeys.VIDA, type = 2).toString().toInt()
        }
        val nivel = runBlocking {
            getData(context, keyInt = PreferencesKeys.NIVEL, type = 2).toString().toInt()
        }
        val vidaMax = nivel * 100
        val vidaFinal = if (vida + ((seconds) / (10 * 60)) * 10 > vidaMax) {
            vidaMax
        } else {
            vida + ((seconds) / (10 * 60)) * 30
        }
        val energia = runBlocking {
            getData(context, keyInt = PreferencesKeys.ENERGIA, type = 2).toString().toInt()
        }
        val energiaMax = nivel * 20
        val energiaFinal = if (energia + ((seconds) / (10 * 60)) > energiaMax) {
            energiaMax
        } else {
            energia + ((seconds) / (10 * 60))
        }
        setData(context, dataInt = vidaFinal, IntKey = PreferencesKeys.VIDA, type = 2)
        setData(context, dataInt = energiaFinal, IntKey = PreferencesKeys.ENERGIA, type = 2)

        viewLogin.setStatsProfile(context)
        update = true
        if (popup) {

        }
    }
    setData(
        context,
        IntKey = PreferencesKeys.TIME_PLAYING,
        dataInt = seconds % (10 * 60),
        type = 2
    )
    return update
}

fun NestedUpdate(context: Context, viewModel: LoginViewModel){
    val packageName = context.packageName

    val uri = Uri.parse("market://details?id=$packageName")
    val intent = Intent(Intent.ACTION_VIEW, uri)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
    }
}

@Composable
fun FormatTime(totalSeconds: Int): String {
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60

    if (minutes > 0) return "%02d:%02ds".format(
        minutes,
        seconds
    ) else if (hours > 0) return "%02d:%02d:%02ds".format(
        hours,
        minutes,
        seconds
    ) else return "%02ds".format(seconds)
}

@RequiresApi(Build.VERSION_CODES.O)
fun calculateTime(fechaInicioString: String, fechaFinString: String): Long {
    try {
        val formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

        val fechaInicio: LocalDateTime = try {
            LocalDateTime.parse(fechaInicioString, formatter1)
        } catch (e: Exception) {
            LocalDateTime.parse(fechaInicioString, formatter2)
        }

        val fechaFin: LocalDateTime = try {
            LocalDateTime.parse(fechaFinString, formatter1)
        } catch (e: Exception) {
            LocalDateTime.parse(fechaFinString, formatter2)
        }

        val diferencia = Duration.between(fechaInicio, fechaFin)
        val segundosTranscurridos = diferencia.toMillis() / 1000

        return segundosTranscurridos
    } catch (e: Exception) {
        //Log.d("Prueba", "calcularMinutosTranscurridos: $e")
        return 0
    }
}


suspend fun attackgenerator(attack: Int, defensa: Int): Float {
    if (attack <= 0 || defensa <= 0) {
        return 0f
    } else {
        val random = (generateRandomNumber(defensa, defensa / 2))
        val _attack = (attack * random / (defensa * 1f))
        //Log.d("Prueba", "attackgenerator: $_attack, random: $random, defensa: $defensa")
        return if (_attack <= 0) {
            0f
        } else {
            _attack.toFloat()
        }
    }
}

@Composable
fun FormatNumber(number: Int): String {
    val formattedNumber = when {
        number >= 1_000_000 -> {
            val decimalFormat = DecimalFormat("#.#")
            "${decimalFormat.format(number / 1_000_000.0)} M"
        }

        else -> number.toString()
    }
    //Log.d("debug", formattedNumber)
    return formattedNumber
}