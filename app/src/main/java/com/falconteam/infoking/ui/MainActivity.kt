package com.falconteam.infoking.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.falconteam.infoking.R
import com.falconteam.infoking.data.network.dto.ranking.RankingRequest
import com.falconteam.infoking.ui.components.PreferencesKeys
import com.falconteam.infoking.ui.components.PreferencesKeys.LAST_CONECTION
import com.falconteam.infoking.ui.components.getCurrentDateTime
import com.falconteam.infoking.ui.components.getData
import com.falconteam.infoking.ui.components.saveData
import com.falconteam.infoking.ui.components.setData
import com.falconteam.infoking.ui.components.setLastTime
import com.falconteam.infoking.ui.navigation.graphs.RootNavGraph
import com.falconteam.infoking.ui.theme.InfoKingTheme
import com.falconteam.infoking.ui.viewmodels.AttackViewModel
import com.falconteam.infoking.ui.viewmodels.LoginViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()
    private val viewAttack: AttackViewModel by viewModels()
    private val context = this@MainActivity

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = getColor(R.color.ic_application_background)
        }
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        super.onCreate(savedInstanceState)
        setContent {
            InfoKingTheme(darkTheme = true) {
                RootNavGraph(navController = rememberNavController())
            }
        }
        lifecycleScope.launch {
            viewModel.startCount(context)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStop() {
        super.onStop()
        setData(
            context,
            dataBoolean = false,
            BooleanKey = PreferencesKeys.OPEN_GAME,
            type = 4
        )
        val batalla = runBlocking {
            val data = getData(
                context,
                keyBoolean = PreferencesKeys.BATTLE_ACTIVE,
                type = 5
            )
            data?.toString()?.toBoolean() ?: false
        }
        if (batalla) {
            viewAttack.putDerrotRanking(
                RankingRequest(
                    runBlocking {
                        getData(context, keyString = PreferencesKeys.ID, type = 1).toString()
                    },
                    runBlocking {
                        getData(
                            context, keyString = PreferencesKeys.PERSONAJE_ID, type = 1
                        ).toString()
                    },
                )
            )
            saveData(context, viewAttack)
            setData(context, dataBoolean = false, BooleanKey = PreferencesKeys.BATTLE_ACTIVE, type = 4)
        }
        viewModel.updateLastConnection(this@MainActivity)
        setData(
            context = this@MainActivity,
            StringKey = LAST_CONECTION,
            dataString = getCurrentDateTime()
        )

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        setLastTime(context, true)
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}