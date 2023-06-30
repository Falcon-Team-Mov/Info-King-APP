package com.falconteam.infoking.data.network.repository

import android.util.Log
import com.falconteam.infoking.data.network.dto.forgotpass.ForgosPassRequest
import com.falconteam.infoking.data.network.dto.forgotpass.ForgotPassCodeRequest
import com.falconteam.infoking.data.network.dto.forgotpass.ForgotPassCodeResponse
import com.falconteam.infoking.data.network.dto.forgotpass.ForgotPassEmailResponse
import com.falconteam.infoking.data.network.dto.forgotpass.ForgotPassResponse
import com.falconteam.infoking.data.network.service.ForgotPassService
import retrofit2.HttpException

class ForgotPassRepository(private val api: ForgotPassService) {
    suspend fun ForgotPassEmail(email: String): ForgotPassCodeResponse {
        try {
            return api.ForgotPassCode(ForgotPassCodeRequest(email))
        } catch (e: HttpException) {
            Log.d("Pruebas", "ForgotPassEmail: $e")
            return ForgotPassCodeResponse("Error con el servidor", false)
        } catch (e: HttpException) {
            if (e.code() == 500)
                return ForgotPassCodeResponse("El correo no existe", false)
            return ForgotPassCodeResponse("Error con el servidor", false)
        }
    }

    suspend fun ForgotPassCode(code: String): ForgotPassEmailResponse {
        try {
            return api.ForgotPassValidCode(code)
        } catch (e: HttpException) {
            return ForgotPassEmailResponse("Codigo invalido", false)
        }
    }

    suspend fun ForgotPass(email: String, password: String): ForgotPassResponse {
        try {
            return api.ForgotPassword(ForgosPassRequest(email, password))
        } catch (e: HttpException) {
            return ForgotPassResponse("Error con el servidor", false)
        }
    }
}