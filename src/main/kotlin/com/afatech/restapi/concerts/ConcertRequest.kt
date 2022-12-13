package com.afatech.restapi.concerts

class ConcertRequest(
    val artist: String,
    val imgThumbnail: String,
    val title: String,
    val description: String,
    val locationName: String,
    val locationCoordinate: String,
    val date: String,
    val time: String,
    val genreMusic: String,
)