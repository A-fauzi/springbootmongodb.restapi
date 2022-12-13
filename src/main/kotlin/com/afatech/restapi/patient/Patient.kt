package com.afatech.restapi.patient

import com.afatech.restapi.Utils
import com.afatech.restapi.Utils.formattedDate
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class Patient(
    @JsonSerialize(using = ToStringSerializer::class)
    val id: ObjectId = ObjectId.get(),
    val name: String,
    val description: String,
    val createdDate: String? = formattedDate,
    val modifiedDate: String? = formattedDate
)
