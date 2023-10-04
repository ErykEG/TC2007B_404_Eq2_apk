package com.example.tc2007b_404_eq2_apk.service

import com.example.tc2007b_404_eq2_apk.model.OrgRegister
import com.example.tc2007b_404_eq2_apk.model.OrgRegisterResponse
import com.example.tc2007b_404_eq2_apk.model.OrgRespList
import com.example.tc2007b_404_eq2_apk.model.Page
import com.example.tc2007b_404_eq2_apk.model.PageList
import com.example.tc2007b_404_eq2_apk.model.Stringid
import com.example.tc2007b_404_eq2_apk.model.UserLogin
import com.example.tc2007b_404_eq2_apk.model.UserLoginResponse
import com.example.tc2007b_404_eq2_apk.model.UserProtectedResponse
import com.example.tc2007b_404_eq2_apk.model.UserRegister
import com.example.tc2007b_404_eq2_apk.model.UserRegistrationResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface PagService {

    companion object {

        val instance: PagService =
            Retrofit.Builder().baseUrl("https://api-android2023-qccf-dev.fl0.io/pag/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PagService::class.java)
    }



    @POST("getPag")
    //@Headers("Authorization: {token}")
    suspend fun loadP(@Body id: Stringid): PageList

}
