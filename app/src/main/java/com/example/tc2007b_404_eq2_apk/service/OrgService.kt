package com.example.tc2007b_404_eq2_apk.service

import com.example.tc2007b_404_eq2_apk.model.OrgRegister
import com.example.tc2007b_404_eq2_apk.model.OrgRegisterResponse
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

interface OrgService {

    companion object {

        val instance: OrgService =
            Retrofit.Builder().baseUrl("https://api-android2023-klhg-dev.fl0.io/orgs/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OrgService::class.java)
    }

    @POST("add")
    suspend fun addOrg(
        @Header("Authorization") token: String,
        @Body org: OrgRegister
    ): OrgRegisterResponse

}