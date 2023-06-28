package com.falconteam.infoking.data.network.repository

import com.falconteam.infoking.data.network.ApiResponse
import com.falconteam.infoking.data.network.dto.login.LoginRequest
import com.falconteam.infoking.data.network.dto.login.LoginResponse
import com.falconteam.infoking.data.network.dto.register.RegisterRequest
import com.falconteam.infoking.data.network.service.LoginService
import com.falconteam.infoking.data.network.service.SignUpService
import retrofit2.HttpException
import java.io.IOException

class LoginRepository(private val api: LoginService) {
    suspend fun Login(data:LoginRequest): ApiResponse<LoginResponse>{
        try{
            val response = api.Login(data)
            return ApiResponse.Success(response)
        }
        catch (e: HttpException){
            if(e.code() === 400){
                return ApiResponse.ErrorWithMessage("Credenciales incorrectas")
            }
            else if(e.code() === 404){
                return ApiResponse.ErrorWithMessage("Verificacion de correo electronico no realizada")
            }
            return ApiResponse.Error(e)
        }
        catch (e: IOException){
            return ApiResponse.Error(e)
        }
    }
}