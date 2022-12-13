package com.afatech.restapi.concerts

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class Concert(
    @JsonSerialize(using = ToStringSerializer::class)
    val id: ObjectId = ObjectId.get(),
    val artist: String,
    val imgThumbnail: String,
    val title: String,
    val description: String,
    val locationName: String,
    val locationCoordinate: String,
    val date: String,
    val time: String,
    val genreMusic: String,
    val createdDate: LocalDateTime = LocalDateTime.now(),
    val modifiedDate: LocalDateTime = LocalDateTime.now()

)