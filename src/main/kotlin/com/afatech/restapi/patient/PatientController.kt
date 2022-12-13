package com.afatech.restapi.patient

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
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/patients")
class PatientController(private val patientRepository: PatientRepository) {
    @GetMapping
    fun getAllPatients(): ResponseEntity<List<Patient>> {
        val patient = patientRepository.findAll()
        return ResponseEntity.ok(patient)
    }

    @GetMapping("/{id}")
    fun getOnePatient(@PathVariable("id") id: String): ResponseEntity<Patient> {
        val patient = patientRepository.findOneById(ObjectId(id))
        return ResponseEntity.ok(patient)
    }

    @PostMapping
    fun createPatient(@RequestBody request: PatientRequest): ResponseEntity<Patient> {
        val patient = patientRepository.save(Patient(name = request.name, description = request.description))
        return ResponseEntity(patient, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updatePatient(@RequestBody request: PatientRequest, @PathVariable("id") id: String): ResponseEntity<Patient> {
        val patient = patientRepository.findOneById(ObjectId(id))
        val updatePatient = patientRepository.save(
            Patient(
                id = patient.id,
                name = request.name,
                description = request.description,
                createdDate = patient.createdDate,
                modifiedDate = LocalDateTime.now()
            )
        )
        return ResponseEntity.ok(updatePatient)
    }

    @DeleteMapping("/{id}")
    fun deletePatient(@PathVariable("id") id: String): ResponseEntity<Patient> {
        patientRepository.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}











