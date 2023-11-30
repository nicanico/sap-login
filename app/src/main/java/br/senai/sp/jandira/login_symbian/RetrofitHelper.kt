package br.senai.sp.jandira.login_symbian

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitHelper {

    private const val baseurl = "http://10.107.144.35:3000"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(baseurl)
        .addConverterFactory(
            GsonConverterFactory
                .create()
        )
        .build()
    fun postUsuarioService(): ApiService {
        return retrofitFactory.create(ApiService::class.java)
    }
}