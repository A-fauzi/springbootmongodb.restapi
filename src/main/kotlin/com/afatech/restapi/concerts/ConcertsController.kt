package com.afatech.restapi.concerts

import org.bson.types.ObjectId
import org.jetbrains.annotations.NotNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/concerts")
class ConcertsController(private val concertsRepository: ConcertsRepository) {
    @GetMapping
    fun getAllConcert(): ResponseEntity<ResponseAllConcerts> {
        val concert = concertsRepository.findAll()
        val responseNotEmpty  = ResponseAllConcerts("Success Get Data", concert)
        val responseEmpty  = ResponseAllConcerts("Sorry Data Is Empty", concert)

        return if (concert.isNotEmpty()) ResponseEntity(responseNotEmpty, HttpStatus.OK)
        else ResponseEntity(responseEmpty, HttpStatus.OK)


    }

    @GetMapping("/{id}")
    fun getOneConcerts(@PathVariable("id") id: String) : ResponseEntity<ResponseConcerts> {
        val concert = concertsRepository.findOneById(ObjectId(id))

        val response = ResponseConcerts("Concert dengan id $id di temukan", concert)
        val responseNot = ResponseConcerts("Maaf Concert dengan id $id Tidak Di Temukan")

        return ResponseEntity(response, HttpStatus.OK)


    }

    @PostMapping
    fun createConcert(@RequestBody request: ConcertRequest): ResponseEntity<ResponseConcerts> {
        val concert = concertsRepository.save(
            Concert(
                artist = request.artist,
                title = request.title,
                description = request.description,
                locationName = request.locationName,
                locationCoordinate = request.locationCoordinate,
                date = request.date,
                time = request.time,
                genreMusic = request.genreMusic,
                imgThumbnail = request.imgThumbnail
            )
        )
        val response  = ResponseConcerts("Data Created", concert)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateConcert(@RequestBody request: ConcertRequest, @PathVariable("id") id: String): ResponseEntity<Concert> {
        val concert = concertsRepository.findOneById(ObjectId(id))
        val updateConcert = concertsRepository.save(
            Concert(
                id = concert.id,
                artist = request.artist,
                imgThumbnail = request.imgThumbnail,
                title = request.title,
                description = request.description,
                locationName = request.locationName,
                locationCoordinate = request.locationCoordinate,
                date = request.date,
                time = request.time,
                genreMusic = request.genreMusic,
                createdDate = concert.createdDate,
                modifiedDate = LocalDateTime.now()
            )
        )
        return ResponseEntity.ok(updateConcert)
    }

    @DeleteMapping("{id}")
    fun deleteConcert(@PathVariable("id") id: String): ResponseEntity<ResponseConcerts> {
        return if (concertsRepository.existsById(id)) {
            concertsRepository.deleteById(id)
            val response = ResponseConcerts("Data Deleted")
            ResponseEntity(response, HttpStatus.OK)
        } else {
            val response = ResponseConcerts("Id Tidak Ditemukan")
            ResponseEntity(response, HttpStatus.NOT_FOUND)
        }
    }
}