package com.afatech.restapi

import java.text.SimpleDateFormat
import java.util.*

object Utils {
    private val dateFormat = SimpleDateFormat("dd MMMM yyyy HH:mm:ss", Locale("id"))
    val formattedDate = dateFormat.format(Date())
}