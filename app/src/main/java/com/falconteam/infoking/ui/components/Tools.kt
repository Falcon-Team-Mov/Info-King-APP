package com.falconteam.infoking.ui.components

import android.app.Application
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
import androidx.lifecycle.ViewModelProvider
import com.falconteam.infoking.data.models.SessionUserData
import com.falconteam.infoking.data.models.StatsUpdate
import com.falconteam.infoking.ui.viewmodels.AttackViewModel
import com.falconteam.infoking.ui.viewmodels.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.text.DecimalFormat
import java.text.NumberFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.ceil
import java.util.Locale
import kotlin.random.Random

@Composable
fun TextResponsiveSize(size: TextUnit): TextUnit {

    val configuration = LocalConfiguration.current

    val scaleFactor = if (configuration.smallestScreenWidthDp >= 600) 1.2f else 0.6f

    return (size * scaleFactor)
}

@RequiresApi(Build.VERSION_CODES.O)
fun saveData(current: Context, viewModel: AttackViewModel) {
    val user = SessionUserData(
        runBlocking {
            getData(current, keyString = PreferencesKeys.ID, type = 1).toString()
        },
        runBlocking {
            getData(
                current,
                keyString = PreferencesKeys.USERNAME,
                type = 1
            ).toString()
        },
        runBlocking {
            getData(current, keyString = PreferencesKeys.EMAIL, type = 1).toString()
        },
        runBlocking {
            getData(current, keyString = PreferencesKeys.ROLE, type = 1).toString()
        },
        runBlocking {
            getData(current, keyInt = PreferencesKeys.EXP, type = 2).toString()
                .toInt()
        },
        runBlocking {
            getData(current, keyInt = PreferencesKeys.NIVEL, type = 2).toString()
                .toInt()
        },
        getCurrentDateTime(),
        runBlocking {
            getData(
                current,
                keyString = PreferencesKeys.CREATED_AT,
                type = 1
            ).toString()
        },
        runBlocking {
            getData(
                current,
                keyInt = PreferencesKeys.TIME_PLAYING,
                type = 2
            ).toString().toInt()
        },
    )
    viewModel.putUser(
        runBlocking {
            getData(current, keyString = PreferencesKeys.ID, type = 1).toString()
        }, user
    )
    viewModel.putStats(
        runBlocking {
            getData(current, keyString = PreferencesKeys._ID, type = 1).toString()
        }, StatsUpdate(
            runBlocking {
                getData(current, keyString = PreferencesKeys.PERSONAJE_ID, type = 1).toString()
            },
            runBlocking {
                getData(current, keyInt = PreferencesKeys.VIDA, type = 2).toString().toInt()
            },
            runBlocking {
                getData(current, keyInt = PreferencesKeys.ATAQUE, type = 2).toString().toInt()
            },
            runBlocking {
                getData(current, keyInt = PreferencesKeys.DEFENSA, type = 2).toString().toInt()
            },
            runBlocking {
                getData(current, keyInt = PreferencesKeys.ENERGIA, type = 2).toString().toInt()
            },
        )
    )
}

fun calculateLevel(current: Context, npc: Int, viewModel: LoginViewModel) {
    var exp = runBlocking {
        getData(current, keyInt = PreferencesKeys.EXP, type = 2).toString().toInt()
    }
    val nivel = runBlocking {
        getData(current, keyInt = PreferencesKeys.NIVEL, type = 2).toString().toInt()
    }
    val vida = runBlocking {
        getData(current, keyInt = PreferencesKeys.MAX_VIDA, type = 2).toString().toInt()
    }
    val ataque = runBlocking {
        getData(current, keyInt = PreferencesKeys.ATAQUE, type = 2).toString().toInt()
    }
    val defensa = runBlocking {
        getData(current, keyInt = PreferencesKeys.DEFENSA, type = 2).toString().toInt()
    }
    val energia = runBlocking {
        getData(current, keyInt = PreferencesKeys.MAX_ENERGIA, type = 2).toString().toInt()
    }

    Log.d(
        "calculateLevel",
        "exp: $exp nivel: $nivel vida: $vida ataque: $ataque defensa: $defensa energia: $energia"
    )

    setData(
        current,
        IntKey = PreferencesKeys.EXP,
        dataInt = exp + expObtain(player = nivel, npc = npc ?: 1),
        type = 2
    )
    exp = runBlocking {
        getData(current, keyInt = PreferencesKeys.EXP, type = 2).toString().toInt()
    }
    if (exp >= expTotal(nivel)) {
        setData(current, IntKey = PreferencesKeys.EXP, dataInt = (exp % expTotal(nivel)), type = 2)
        setData(current, IntKey = PreferencesKeys.NIVEL, dataInt = nivel + 1, type = 2)
        setData(
            current,
            IntKey = PreferencesKeys.MAX_VIDA,
            dataInt = vida + runBlocking { generateRandomNumber(ceil((vida / 8).toDouble()).toInt()) },
            type = 2
        )
        setData(
            current,
            IntKey = PreferencesKeys.ATAQUE,
            dataInt = ataque + runBlocking { generateRandomNumber(ceil((ataque / 8).toDouble()).toInt()) },
            type = 2
        )
        setData(
            current,
            IntKey = PreferencesKeys.DEFENSA,
            dataInt = defensa + runBlocking { generateRandomNumber(ceil((defensa / 8).toDouble()).toInt()) },
            type = 2
        )
        setData(
            current,
            IntKey = PreferencesKeys.MAX_ENERGIA,
            dataInt = energia + runBlocking { generateRandomNumber(ceil((energia / 8).toDouble()).toInt()) },
            type = 2
        )
        viewModel.putUserMaxData(current)
    }

}

fun expObtain(player: Int, npc: Int): Int {
    val result = when {
        player < npc -> 4
        player == npc -> 3
        player - 2 > npc -> 1
        else -> 2
    }
    Log.d("Pruebas", "expObtain: $result")
    return result
}

fun expTotal(nivel: Int): Int {
    return 75 * nivel
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
    return if (max == 0 || max == 1) 1 else withContext(Dispatchers.Default) {
        val randomNumber = Random.nextInt(min, max)
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

fun NestedUpdate(context: Context, viewModel: LoginViewModel) {
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
fun formatNumber(number: Int): String {
    val formattedNumber = when {
        number >= 1_000_000 -> {
            val decimalFormat = DecimalFormat("#.#")
            val formatted = decimalFormat.format(number / 1_000_000.0)
            val commaFormatted = NumberFormat.getNumberInstance(Locale.US).format(formatted.toDouble())
            "$commaFormatted M"
        }
        number in 1_000..999_999 -> {
            val decimalFormat = DecimalFormat("#.##")
            val formatted = decimalFormat.format(number / 1_000.0)
            val commaFormatted = NumberFormat.getNumberInstance(Locale.US).format(formatted.toDouble())
            "$commaFormatted k"
        }
        else -> number.toString()
    }
    // Log.d("debug", formattedNumber)
    return formattedNumber
}

@Composable
fun formatNumberWithComma(number: Int): String {
    val formattedNumber = when {
        number >= 1_000_000 -> {
            val thousands = String.format("%,d", number / 1_000)
            "${thousands}k"
        }
        else -> String.format("%,d", number)
    }
    return formattedNumber
}

