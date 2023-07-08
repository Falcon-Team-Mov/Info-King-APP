package com.falconteam.infoking.ui.components

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.falconteam.infoking.data.models.LoginDataResponse
import com.falconteam.infoking.data.models.RankingUserInfo
import com.falconteam.infoking.data.network.dto.login.LoginResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking


object PreferencesKeys {
    val ID = stringPreferencesKey("id")
    val USERNAME = stringPreferencesKey("username")
    val EMAIL = stringPreferencesKey("email")
    val ROLE = stringPreferencesKey("role")
    val EXP = intPreferencesKey("exp")
    val NIVEL = intPreferencesKey("nivel")
    val LAST_CONECTION = stringPreferencesKey("last_conection")
    val CREATED_AT = stringPreferencesKey("created_at")
    val TIME_PLAYING = intPreferencesKey("time_playing")

    val TOKEN = stringPreferencesKey("token")

    val _ID = stringPreferencesKey("_id")
    val PERSONAJE_ID = stringPreferencesKey("personaje_id")
    val VIDA = intPreferencesKey("vida")
    val ATAQUE = intPreferencesKey("ataque")
    val DEFENSA = intPreferencesKey("defensa")
    val ENERGIA = intPreferencesKey("energia")

    val MAX_VIDA = intPreferencesKey("max_vida")
    val MAX_ENERGIA = intPreferencesKey("max_energia")

    val NOMBRE = stringPreferencesKey("nombre")
    val BUFF = doublePreferencesKey("buff")
    val NERF = doublePreferencesKey("nerf")
    val IMAGE_2D = stringPreferencesKey("image_2d")
    val IMAGE_3D = stringPreferencesKey("image_3d")

    val _ID_1 = stringPreferencesKey("_id_1")
    val NOMBRE_1 = stringPreferencesKey("nombre_1")
    val CANTIDAD_1 = intPreferencesKey("cantidad_1")

    val _ID_2 = stringPreferencesKey("_id_2")
    val NOMBRE_2 = stringPreferencesKey("nombre_2")
    val CANTIDAD_2 = intPreferencesKey("cantidad_2")

    val _ID_3 = stringPreferencesKey("_id_3")
    val NOMBRE_3 = stringPreferencesKey("nombre_3")
    val CANTIDAD_3 = intPreferencesKey("cantidad_3")

    val _ID_4 = stringPreferencesKey("_id_4")
    val NOMBRE_4 = stringPreferencesKey("nombre_4")
    val CANTIDAD_4 = intPreferencesKey("cantidad_4")
    val OPEN_GAME = booleanPreferencesKey("open_game")

    val RANKING_USERNAME = stringPreferencesKey("ranking_username")
    val RANKING_ICON = stringPreferencesKey("ranking_icon")
    val RANKING_NIVEL = intPreferencesKey("ranking_nivel")
    val RANKING_ATAQUE = intPreferencesKey("ranking_ataque")
    val RANKING_DEFENSA = intPreferencesKey("ranking_defensa")

    val BATTLE_ACTIVE = booleanPreferencesKey("battle_active")
    val RANKING_VICTORIAS = intPreferencesKey("ranking_victorias")
    val RANKING_DERROTAS = intPreferencesKey("ranking_derrotas")
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "IKG_AUTH")

fun ClearData(
    context: Context,
) = runBlocking {
    context.dataStore.edit { preference ->
        preference.clear()
    }
}

fun setFullData(
    context: Context,
    data: LoginResponse,
) {
    setData(context, data.user.id ?: "", PreferencesKeys.ID)
    setData(context, data.user.username ?: "", PreferencesKeys.USERNAME)
    setData(context, data.user.email ?: "", PreferencesKeys.EMAIL)
    setData(context, data.user.role ?: "", PreferencesKeys.ROLE)
    setData(context, dataInt = data.user.exp ?: -1, IntKey = PreferencesKeys.EXP, type = 2)
    setData(context, dataInt = data.user.nivel ?: -1, IntKey = PreferencesKeys.NIVEL, type = 2)
    setData(context, data.user.last_conection ?: "", PreferencesKeys.LAST_CONECTION)
    setData(context, data.user.created_at ?: "", PreferencesKeys.CREATED_AT)
    setData(
        context,
        dataInt = data.user.time_playing ?: -1, IntKey = PreferencesKeys.TIME_PLAYING,
        type = 2
    )
    setData(context, data.token ?: "", PreferencesKeys.TOKEN)

    setData(context, data.stats._id ?: "", PreferencesKeys._ID)
    setData(context, data.stats.personaje_id ?: "", PreferencesKeys.PERSONAJE_ID)
    setData(context, dataInt = data.stats.vida ?: -1, IntKey = PreferencesKeys.VIDA, type = 2)
    setData(context, dataInt = data.stats.ataque ?: -1, IntKey = PreferencesKeys.ATAQUE, type = 2)
    setData(context, dataInt = data.stats.defensa ?: -1, IntKey = PreferencesKeys.DEFENSA, type = 2)
    setData(context, dataInt = data.stats.energia ?: -1, IntKey = PreferencesKeys.ENERGIA, type = 2)

    setData(context, dataInt = data.stats.max_vida ?: -1, IntKey = PreferencesKeys.MAX_VIDA, type = 2)
    setData(context, dataInt = data.stats.max_energia ?: -1, IntKey = PreferencesKeys.MAX_ENERGIA, type = 2)

    setData(context, data.personaje.nombre ?: "", PreferencesKeys.NOMBRE)
    setData(
        context,
        dataDouble = data.personaje.buff ?: -1.0,
        DoubleKey = PreferencesKeys.BUFF,
        type = 3
    )
    setData(
        context,
        dataDouble = data.personaje.nerf ?: -1.0,
        DoubleKey = PreferencesKeys.NERF,
        type = 3
    )
    setData(context, data.personaje.image._2d ?: "", PreferencesKeys.IMAGE_2D)
    setData(context, data.personaje.image._3d ?: "", PreferencesKeys.IMAGE_3D)

    setData(context, data.poderes[0]._id ?: "", PreferencesKeys._ID_1)
    setData(context, data.poderes[0].nombre ?: "", PreferencesKeys.NOMBRE_1)
    setData(
        context,
        dataInt = data.poderes[0].cantidad ?: -1, IntKey = PreferencesKeys.CANTIDAD_1,
        type = 2
    )

    setData(context, data.poderes[1]._id ?: "", PreferencesKeys._ID_2)
    setData(context, data.poderes[1].nombre ?: "", PreferencesKeys.NOMBRE_2)
    setData(
        context,
        dataInt = data.poderes[1].cantidad ?: -1, IntKey = PreferencesKeys.CANTIDAD_2,
        type = 2
    )

    setData(context, data.poderes[2]._id ?: "", PreferencesKeys._ID_3)
    setData(context, data.poderes[2].nombre ?: "", PreferencesKeys.NOMBRE_3)
    setData(
        context,
        dataInt = data.poderes[2].cantidad ?: -1, IntKey = PreferencesKeys.CANTIDAD_3,
        type = 2
    )

    setData(context, data.poderes[3]._id ?: "", PreferencesKeys._ID_4)
    setData(context, data.poderes[3].nombre ?: "", PreferencesKeys.NOMBRE_4)
    setData(
        context,
        dataInt = data.poderes[3].cantidad ?: -1, IntKey = PreferencesKeys.CANTIDAD_4,
        type = 2
    )
}

fun setFullDataUser(
    context: Context,
    data: LoginDataResponse,
) {
    setData(context, data.id ?: "", PreferencesKeys.ID)
    setData(context, data.name ?: "", PreferencesKeys.USERNAME)
    setData(context, data.email ?: "", PreferencesKeys.EMAIL)
    setData(context, dataInt = data.exp ?: -1, IntKey = PreferencesKeys.EXP, type = 2)
    setData(context, dataInt = data.nivel ?: -1, IntKey = PreferencesKeys.NIVEL, type = 2)
    setData(context, data.last_conection ?: "", PreferencesKeys.LAST_CONECTION)
    setData(
        context,
        dataInt = data.time_playing ?: -1, IntKey = PreferencesKeys.TIME_PLAYING,
        type = 2
    )
    //Log.d("Tiempos", "setFullDataUser: ${data.time_playing}")
    setData(context, data.img, PreferencesKeys.IMAGE_3D)
    setData(context, dataInt = data.stats.vida, IntKey = PreferencesKeys.VIDA, type = 2)
    setData(context, dataInt = data.stats.ataque, IntKey = PreferencesKeys.ATAQUE, type = 2)
    setData(context, dataInt = data.stats.defensa, IntKey = PreferencesKeys.DEFENSA, type = 2)
    setData(context, dataInt = data.stats.energia, IntKey = PreferencesKeys.ENERGIA, type = 2)

    setData(context, dataInt = data.stats.max_vida ?: -1, IntKey = PreferencesKeys.MAX_VIDA, type = 2)
    setData(context, dataInt = data.stats.max_energia ?: -1, IntKey = PreferencesKeys.MAX_ENERGIA, type = 2)
}

fun setFullDataRanking(
    context: Context,
    data: RankingUserInfo
) {
    setData(context, data.username, PreferencesKeys.RANKING_USERNAME)
    setData(context, data.icon, PreferencesKeys.RANKING_ICON)
    setData(context, dataInt = data.nivel, IntKey = PreferencesKeys.RANKING_NIVEL, type = 2)
    setData(context, dataInt = data.ataque, IntKey = PreferencesKeys.RANKING_ATAQUE, type = 2)
    setData(context, dataInt = data.defensa, IntKey = PreferencesKeys.RANKING_DEFENSA, type = 2)
    setData(context, dataInt = data.victorias, IntKey = PreferencesKeys.RANKING_VICTORIAS, type = 2)
    setData(context, dataInt = data.derrotas, IntKey = PreferencesKeys.RANKING_DERROTAS, type = 2)
}

fun setData(
    context: Context,
    dataString: String = "",
    StringKey: Preferences.Key<String> = PreferencesKeys.TOKEN,
    dataInt: Int = 0,
    IntKey: Preferences.Key<Int> = PreferencesKeys.VIDA,
    dataDouble: Double = 0.0,
    DoubleKey: Preferences.Key<Double> = PreferencesKeys.BUFF,
    dataBoolean: Boolean = false,
    BooleanKey: Preferences.Key<Boolean> = PreferencesKeys.OPEN_GAME,
    type: Int = 1
) = runBlocking {
    context.dataStore.edit { preferences ->
        when (type) {
            1 -> {
                preferences[StringKey] = dataString
            }

            2 -> {
                preferences[IntKey] = dataInt
            }

            3 -> {
                preferences[DoubleKey] = dataDouble
            }

            4 -> {
                preferences[BooleanKey] = dataBoolean
            }
        }
    }
}

fun getToken(context: Context): Flow<String?> {
    return context.dataStore.data
        .catch { exception ->
            if (exception is NullPointerException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferencesKeys.TOKEN]
        }
}

suspend fun getData(
    context: Context,
    keyString: Preferences.Key<String> = PreferencesKeys.TOKEN,
    keyInt: Preferences.Key<Int> = PreferencesKeys.VIDA,
    keyDouble: Preferences.Key<Double> = PreferencesKeys.BUFF,
    keyBoolean: Preferences.Key<Boolean> = PreferencesKeys.OPEN_GAME,
    type: Int = 1
): Any? {
    val preferences = context.dataStore.data.first()

    return when (type) {
        1 -> preferences[keyString] ?: ""
        2 -> preferences[keyInt] ?: -1
        3 -> preferences[keyDouble] ?: -1.0
        4 -> preferences[keyDouble]?.toFloat()
        5 -> preferences[keyBoolean]
        else -> null
    }
}