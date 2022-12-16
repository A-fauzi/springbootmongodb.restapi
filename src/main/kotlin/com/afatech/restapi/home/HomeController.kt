package com.afatech.restapi.home

import com.afatech.restapi.concerts.ResponseConcerts
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class HomeController {
    @GetMapping
    fun getMessage(): ResponseEntity<Response> {
        val response = Response("Silahkan Akses Endpoint", "Akhmad Fauzi", "15 Desember 2022")
        return ResponseEntity(response, HttpStatus.OK)
    }
}