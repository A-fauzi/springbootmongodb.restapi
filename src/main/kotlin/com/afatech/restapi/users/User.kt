package com.afatech.restapi.users

import com.afatech.restapi.Utils.formattedDate
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

    val gender: String,

    val title: String,

    val description: String,

    val createdDate: String? = formattedDate,

    val modifiedDate: String? = formattedDate

)
