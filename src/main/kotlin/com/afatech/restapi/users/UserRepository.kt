package com.afatech.restapi.users

import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository: MongoRepository<User, String> {
    fun findOneById(id: String): User

    override fun deleteAll() {
        TODO("Not yet implemented")
    }
}