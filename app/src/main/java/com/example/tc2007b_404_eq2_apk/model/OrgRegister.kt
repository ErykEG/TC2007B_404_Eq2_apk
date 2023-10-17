package com.example.tc2007b_404_eq2_apk.model

data class OrgRegister(
    val name: String,
    val description: String,
    val email: String,
    val password: String,
    val validarpassword: String,
    val token: String,
    val img: String,
    val linkb1: String,
    val linkb2: String,
    val linkb4: String,
    val orgTags: Array<String>
)
