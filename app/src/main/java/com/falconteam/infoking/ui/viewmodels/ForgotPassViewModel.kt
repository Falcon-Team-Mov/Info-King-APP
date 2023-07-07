package com.falconteam.infoking.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.infoking.RetrofitApplication
import kotlinx.coroutines.launch

class ForgotPassViewModel() : ViewModel() {
    val msg = mutableStateMapOf<Int, String>()
    val valid = mutableStateMapOf<Int, Boolean>()

    val repository_ForgotPass = RetrofitApplication()._forgotPassRepository

    fun ForgotPassEmail(email: String) {
        viewModelScope.launch {
            try {
                val value = repository_ForgotPass.ForgotPassEmail(email.trim())
                msg[0] = value.msg
                valid[0] = value.valid
            } catch (e: Exception) {
                msg[0] = "Correo no valido"
                valid[0] = false
            }
        }
    }

    fun ForgotPassCode(code: String) {
        try {
            viewModelScope.launch {
                val value = repository_ForgotPass.ForgotPassCode(code)
                msg[1] = value.msg
                valid[1] = value.valid
            }
        } catch (e: Exception) {
            msg[1] = "Codigo invalido"
            valid[1] = false
        }
    }

    fun ForgotPass(email: String, password: String) {
        viewModelScope.launch {
            try {
                val value = repository_ForgotPass.ForgotPass(email, password)
                msg[2] = value.msg
                valid[2] = value.valid
            } catch (e: Exception) {
                msg[2] = "Error con el servidor"
                valid[2] = false
            }
        }
    }
}