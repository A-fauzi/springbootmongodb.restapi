package com.afatech.restapi.concerts

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface ConcertsRepository: MongoRepository<Concert, String> {
    fun findOneById(id: ObjectId): Concert
    fun findConcertByGenreMusic(name: String): List<Concert>
    fun countConcertByGenreMusic(name: String): Long
    override fun deleteAll() {
        TODO("Not yet implemented")
    }
}