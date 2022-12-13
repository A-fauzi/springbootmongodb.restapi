package com.afatech.restapi.users

data class UserResponse(
    val message: String,
    val data: User? = null
)