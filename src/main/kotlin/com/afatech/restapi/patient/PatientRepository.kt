package com.afatech.restapi.patient

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface PatientRepository : MongoRepository<Patient, String> {
    fun findOneById(id: ObjectId) : Patient
    override fun deleteAll() {
        TODO("Not yet implemented")
    }
}