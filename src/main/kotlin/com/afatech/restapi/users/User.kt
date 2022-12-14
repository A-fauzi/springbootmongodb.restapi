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

    val photoUrl: String? = null,

    val gender: String,

    val title: String? = null,

    val followers: List<User>? = null,
    val following: List<User>? = null,

    val description: String? = null,

    val pathStorageProfile: String? = null,

    val createdDate: String? = formattedDate,

    val modifiedDate: String? = formattedDate

)
