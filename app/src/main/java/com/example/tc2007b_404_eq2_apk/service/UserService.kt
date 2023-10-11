package com.example.tc2007b_404_eq2_apk.service

import com.example.tc2007b_404_eq2_apk.model.OrgRespList
import com.example.tc2007b_404_eq2_apk.model.Star
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

interface UserService {

    companion object {

        val instance: UserService =
            Retrofit.Builder().baseUrl("https://api-android2023-qccf-dev.fl0.io/users/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserService::class.java)
    }

    @POST("register")
    suspend fun insertUser(@Body user: UserRegister): UserRegistrationResponse

    @POST("login")
    suspend fun loginUser(@Body user: UserLogin): UserLoginResponse

    @GET("protected")
    //@Headers("Authorization: {token}")
    suspend fun protectedRoute(@Header("Authorization") token: String): UserProtectedResponse

    @GET("getUserFavoriteOrganizations")
    //@Headers("Authorization: {token}")
    suspend fun getFav(@Header("Authorization") token: String): OrgRespList

    @POST("hasFav")
    //@Headers("Authorization: {token}")
    suspend fun boolFav(@Header("Authorization") token: String): Star

}
