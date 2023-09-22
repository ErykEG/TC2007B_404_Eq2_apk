package com.example.tc2007b_404_eq2_apk.model

data class UserLoginResponse(
    val token: String? = "",
    var message: String? = null,
    val isAdmin: Boolean = false
)