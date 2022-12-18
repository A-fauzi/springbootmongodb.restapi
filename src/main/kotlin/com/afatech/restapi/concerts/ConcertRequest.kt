package com.afatech.restapi.concerts

class ConcertRequest(
    val artist: String,
    val imgThumbnail: String,
    val title: String,
    val description: String,
    val locationName: String,
    val latitude: String,
    val longitude: String,
    val date: String,
    val time: String,
    val genreMusic: String,
)