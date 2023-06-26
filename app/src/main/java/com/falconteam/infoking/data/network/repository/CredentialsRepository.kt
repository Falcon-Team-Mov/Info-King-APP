package com.example.laboratorio11.repository

import com.falconteam.infoking.data.network.ApiResponse
import com.falconteam.infoking.data.network.dto.login.LoginRequest
import com.falconteam.infoking.data.network.dto.register.RegisterRequest
import com.falconteam.infoking.data.network.service.AuthService
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class CredentialsRepository(private val api: AuthService) {

//    suspend fun login(email: String, password: String): ApiResponse<String> {
//        try {
//            val response = api.login(LoginRequest(email, password))
//            return ApiResponse.Success(response.token)
//
//        } catch (e: HttpException){
//            if(e.code() === 400) {
//
//                return ApiResponse.ErrorWithMessage("Invalid email or password")
//            }
//
//            return ApiResponse.Error(e)
//        }catch (e: IOException){
//            return ApiResponse.Error(e)
//        }
//    }

    suspend fun register(name: String, email: String, password: String, personaje: String): ApiResponse<String>{
        try{
            val response = api.register(RegisterRequest(username = name, email = email, password = password, personaje = personaje))
            return ApiResponse.Success(response.message)

        } catch (e: HttpException){

            if(e.code() === 400){
                return ApiResponse.ErrorWithMessage("Invalid fields, username, email, password or personaje")
            }
            return ApiResponse.Error(e)
        } catch (e: IOException){
            return ApiResponse.Error(e)

        }
    }
}