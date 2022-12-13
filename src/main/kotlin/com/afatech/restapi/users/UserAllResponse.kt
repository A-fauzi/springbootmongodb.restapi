package com.afatech.restapi.users

data class UserAllResponse(
    val message: String,
    val data: List<User>? = null
)