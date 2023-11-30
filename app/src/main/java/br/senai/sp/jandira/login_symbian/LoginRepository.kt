package com.example.symbiancadastro.services

import br.senai.sp.jandira.login_symbian.RetrofitHelper
import com.google.gson.JsonObject
import retrofit2.Response

class LoginRepository {

    private val apiService = RetrofitHelper.postUsuarioService()

    suspend fun loginUsuario(email: String?, senha: String?, foto_perfil: String?): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("login", email)
            addProperty("senha", senha)
            addProperty("imagem", foto_perfil)
        }

        return apiService.createUser(requestBody)
    }
}