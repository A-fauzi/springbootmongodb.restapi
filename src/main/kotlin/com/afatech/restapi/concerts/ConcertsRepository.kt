package com.afatech.restapi.concerts

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface ConcertsRepository: MongoRepository<Concert, String> {
    fun findOneById(id: ObjectId): Concert

    fun findConcertByGenreMusic(name: String): List<Concert>
    override fun deleteAll() {
        TODO("Not yet implemented")
    }
}