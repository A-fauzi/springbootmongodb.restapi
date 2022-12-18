package com.afatech.restapi.concerts

import com.afatech.restapi.Utils.formattedDate
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/concerts")
class ConcertsController(private val concertsRepository: ConcertsRepository) {
    @GetMapping
    fun getAllConcert(): ResponseEntity<ResponseAllConcerts> {
        val concert = concertsRepository.findAll()
        val count = concert.count()
        val responseNotEmpty = ResponseAllConcerts("Success Get Data", count, concert)
        val responseEmpty = ResponseAllConcerts("Sorry Data Is Empty", count)

        return if (concert.isNotEmpty()) ResponseEntity(responseNotEmpty, HttpStatus.OK)
        else ResponseEntity(responseEmpty, HttpStatus.OK)
    }

    @GetMapping("/genre")
    fun getConcertByGenre(@RequestParam(name = "genre") genre: String): ResponseEntity<ResponseAllConcerts> {
        val findGenre = concertsRepository.findConcertByGenreMusic(genre)
        val count = findGenre.count()
        return if (findGenre.isNotEmpty()) {
            val response = ResponseAllConcerts("Data yang anda cari", count, findGenre)
            ResponseEntity(response, HttpStatus.OK)
        } else {
            val response = ResponseAllConcerts("Sorry data not found", count)
            ResponseEntity(response, HttpStatus.OK)
        }

    }


    @GetMapping("/artist")
    fun getArtis(@RequestParam(name = "name") name: String): ResponseEntity<ResponseAllConcerts> {
        val artis = concertsRepository.findByArtistRegex(name)
        val count = artis.count()
        val response = ResponseAllConcerts("Hasil pencarian", count, artis)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getOneConcerts(@PathVariable("id") id: String): ResponseEntity<ResponseConcerts> {
        return if (concertsRepository.existsById(id)) {
            val concert = concertsRepository.findOneById(ObjectId(id))
            val response = ResponseConcerts("Concert dengan id $id di temukan", concert)
            ResponseEntity(response, HttpStatus.OK)
        } else {
            val responseNot = ResponseConcerts("Maaf Concert dengan id $id Tidak Di Temukan")
            ResponseEntity(responseNot, HttpStatus.OK)
        }
    }

    @PostMapping
    fun createConcert(@RequestBody request: ConcertRequest): ResponseEntity<ResponseConcerts> {
        val concert = concertsRepository.save(
            Concert(
                artist = request.artist,
                title = request.title,
                description = request.description,
                locationName = request.locationName,
                latitude = request.latitude,
                date = request.date,
                time = request.time,
                genreMusic = request.genreMusic,
                imgThumbnail = request.imgThumbnail,
                longitude = request.longitude
            )
        )
        val response = ResponseConcerts("Data Created", concert)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateConcert(
        @RequestBody request: ConcertRequest,
        @PathVariable("id") id: String
    ): ResponseEntity<ResponseConcerts> {
        if (concertsRepository.existsById(id)) {
            val concert = concertsRepository.findOneById(ObjectId(id))
            val updateConcert = concertsRepository.save(
                Concert(
                    id = concert.id,
                    artist = request.artist,
                    imgThumbnail = request.imgThumbnail,
                    title = request.title,
                    description = request.description,
                    locationName = request.locationName,
                    latitude = request.latitude,
                    longitude = request.longitude,
                    date = request.date,
                    time = request.time,
                    genreMusic = request.genreMusic,
                    createdDate = concert.createdDate,
                    modifiedDate = formattedDate
                )
            )
            val response = ResponseConcerts("Data Updated", updateConcert)
            return ResponseEntity(response, HttpStatus.CREATED)
        } else {
            val response = ResponseConcerts("Sorry Concert Not Found")
            return ResponseEntity(response, HttpStatus.NOT_FOUND)
        }
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