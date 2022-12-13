package com.afatech.restapi.concerts

data class ResponseAllConcerts(
    val message: String,
    val count: Int,
    val data: List<Concert>? = null
)
