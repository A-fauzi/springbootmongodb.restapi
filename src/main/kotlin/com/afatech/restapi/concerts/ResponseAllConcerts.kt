package com.afatech.restapi.concerts

data class ResponseAllConcerts(
    val message: String,
    val data: List<Concert>? = null
)
