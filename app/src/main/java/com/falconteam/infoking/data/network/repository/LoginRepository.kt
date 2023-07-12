package com.falconteam.infoking.data.network.repository

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.falconteam.infoking.data.models.ConnectionData
import com.falconteam.infoking.data.models.LoginDataResponse
import com.falconteam.infoking.data.models.StatsProfileData
import com.falconteam.infoking.data.network.ApiResponse
import com.falconteam.infoking.data.network.dto.login.LoginRequest
import com.falconteam.infoking.data.network.dto.login.LoginResponse
import com.falconteam.infoking.data.network.service.LoginService
import com.falconteam.infoking.ui.components.PreferencesKeys
import com.falconteam.infoking.ui.components.getCurrentDateTime
import com.falconteam.infoking.ui.components.getData
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import java.io.IOException

class LoginRepository(private val api: LoginService) {
    suspend fun Login(data: LoginRequest): ApiResponse<LoginResponse> {
        try {
            val response = api.Login(data)
            return ApiResponse.Success(response)
        } catch (e: HttpException) {
            if (e.code() === 400) {
                return ApiResponse.ErrorWithMessage("Credenciales incorrectas")
            } else if (e.code() === 404) {
                return ApiResponse.ErrorWithMessage("Verificacion de correo electronico no realizada")
            }
            return ApiResponse.Error(e)
        } catch (e: IOException) {
            return ApiResponse.Error(e)
        }
    }

    suspend fun getVersion(): String {
        try {
            val response = api.getVersion()
            return response.version
        } catch (e: HttpException) {
            return "0.0.0"
        } catch (e: IOException) {
            return "0.0.0"
        }
    }

    suspend fun getUserData(id: String): ApiResponse<LoginDataResponse> {
        try {
            val response = api.getUserData(id)
            return ApiResponse.Success(response)
        } catch (e: HttpException) {
            if (e.code() === 400) {
                return ApiResponse.ErrorWithMessage("Credenciales incorrectas")
            } else if (e.code() === 404) {
                return ApiResponse.ErrorWithMessage("Verificacion de correo electronico no realizada")
            }
            return ApiResponse.Error(e)
        } catch (e: IOException) {
            return ApiResponse.Error(e)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun updateLastConnection(context: Context) {
        try {
            val id = runBlocking {
                getData(
                    context,
                    keyString = PreferencesKeys.ID,
                    type = 1
                )
            }.toString()
            val date = getData(
                context,
                keyInt = PreferencesKeys.TIME_PLAYING,
                type = 2
            ).toString().toInt()
            api.setConnection(id, ConnectionData(getCurrentDateTime(), date))
        } catch (e: HttpException) {
            if (e.code() === 400) {
                return
            } else if (e.code() === 404) {
                return
            }
            return
        } catch (e: IOException) {
            return
        }
    }

    suspend fun setStatsProfile(context: Context) {
        try {
            val id = runBlocking {
                getData(
                    context,
                    keyString = PreferencesKeys._ID,
                    type = 1
                )
            }.toString()
            val vida = runBlocking {
                getData(
                    context,
                    keyInt = PreferencesKeys.VIDA,
                    type = 2
                )
            }.toString().toInt()
            val energia = runBlocking {
                getData(
                    context,
                    keyInt = PreferencesKeys.ENERGIA,
                    type = 2
                )
            }.toString().toInt()
            api.setStatsProfile(id, StatsProfileData(vida, energia))
        } catch (e: HttpException) {
            if (e.code() === 400) {
                return
            } else if (e.code() === 404) {
                return
            }
            return
        } catch (e: IOException) {
            return
        }
    }

    suspend fun putUserMaxData(context: Context) {
        try {
            val id = runBlocking {
                getData(
                    context,
                    keyString = PreferencesKeys._ID,
                    type = 1
                )
            }.toString()
            val vidaMax = runBlocking {
                getData(
                    context,
                    keyInt = PreferencesKeys.MAX_VIDA,
                    type = 2
                )
            }.toString().toInt()
            val energiaMax = runBlocking {
                getData(
                    context,
                    keyInt = PreferencesKeys.MAX_ENERGIA,
                    type = 2
                )
            }.toString().toInt()
            api.setMaxStatsProfile(id, StatsProfileData(vidaMax, energiaMax))
        } catch (e: HttpException) {
            if (e.code() === 400) {
                return
            } else if (e.code() === 404) {
                return
            }
            return
        } catch (e: IOException) {
            return
        }
    }
}