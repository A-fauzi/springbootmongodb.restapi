package com.afatech.restapi.concerts

data class ResponseConcerts(
    val message: String,
    val data: Concert? = null
)
