package com.afatech.restapi.users

class UserRequest(
    val id: String,

    val name: String,

    val phone: String,

    val email: String,

    val photoUrl: String? = null,

    val gender: String,

    val title: String? = null,

    val followers: List<User>? = null,
    val following: List<User>? = null,

    val description: String? = null,

    val pathStorageProfile: String? = null
)