package com.falconteam.infoking.ui.components

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.falconteam.infoking.data.models.npc
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


object PreferencesKeysBattle {
    val ID_NPC = stringPreferencesKey("npc_id")
    val NOMBRE_NPC = stringPreferencesKey("npc_nombre")
    val VIDA_NPC = intPreferencesKey("npc_vida")
    val DEFENSA_NPC = intPreferencesKey("npc_defensa")
    val ATAQUE_NPC = intPreferencesKey("npc_ataque")
    val IMAGEN_NPC = stringPreferencesKey("npc_imagen")
}

val Context.dataStoreBattle: DataStore<Preferences> by preferencesDataStore(name = "IKG_BATTLE")

fun ClearDataBattle(
    context: Context,
) = runBlocking {
    context.dataStoreBattle.edit { preference ->
        preference.clear()
    }
}

fun setFullDataBattle(
    context: Context,
    data: npc,
) {
    setDataBattle(context, data.id ?: "", PreferencesKeysBattle.ID_NPC)
    setDataBattle(context, data.nombre ?: "", PreferencesKeysBattle.NOMBRE_NPC)
    setDataBattle(
        context,
        dataInt = data.vida ?: -1,
        IntKey = PreferencesKeysBattle.VIDA_NPC,
        type = 2
    )
    setDataBattle(
        context,
        dataInt = data.defensa ?: -1,
        IntKey = PreferencesKeysBattle.DEFENSA_NPC,
        type = 2
    )
    setDataBattle(
        context,
        dataInt = data.ataque ?: -1,
        IntKey = PreferencesKeysBattle.ATAQUE_NPC,
        type = 2
    )
    setDataBattle(context, dataString = data.imagen ?: "", PreferencesKeysBattle.IMAGEN_NPC)
}

fun setDataBattle(
    context: Context,
    dataString: String = "",
    StringKey: Preferences.Key<String> = PreferencesKeysBattle.IMAGEN_NPC,
    dataInt: Int = 0,
    IntKey: Preferences.Key<Int> = PreferencesKeysBattle.VIDA_NPC,
    type: Int = 1
) = runBlocking {
    context.dataStoreBattle.edit { preferences ->
        when (type) {
            1 -> {
                preferences[StringKey] = dataString
            }

            2 -> {
                preferences[IntKey] = dataInt
            }
        }
    }
}

suspend fun getDataBattle(
    context: Context,
    keyString: Preferences.Key<String> = PreferencesKeys.TOKEN,
    keyInt: Preferences.Key<Int> = PreferencesKeys.VIDA,
    keyDouble: Preferences.Key<Double> = PreferencesKeys.BUFF,
    type: Int = 1
): Any? {
    val preferences = context.dataStoreBattle.data.first()

    return when (type) {
        1 -> preferences[keyString] ?: ""
        2 -> preferences[keyInt] ?: -1
        3 -> preferences[keyDouble] ?: -1.0
        4 -> preferences[keyDouble]?.toFloat()
        else -> null
    }
}