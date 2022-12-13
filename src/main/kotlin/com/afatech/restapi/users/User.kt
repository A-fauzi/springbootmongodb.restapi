package com.afatech.restapi.users

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class User(
    @Id
    val id: String,

    val name: String,

    val phone: String,

    val email: String,

    val photoUrl: String,

    val createdDate: LocalDateTime = LocalDateTime.now(),

    val modifiedDate: LocalDateTime = LocalDateTime.now()

)
