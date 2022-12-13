package com.afatech.restapi

import com.afatech.restapi.patient.Patient
import com.afatech.restapi.patient.PatientRepository
import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PatientControllerIntTest @Autowired constructor(
    private val patientRepository: PatientRepository,
    private val restTemplate: TestRestTemplate
) {
    private val defaultPatientId = ObjectId.get()

    @LocalServerPort
    protected var port: Int = 27017

    @BeforeEach
    fun setUp() {
        patientRepository.deleteAll()
    }


    private fun getRootUrl(): String = "http://localhost:$port/patients"

    private fun saveOnePatient() = patientRepository.save(Patient(defaultPatientId, "Tes Name", "Tes Description"))

    @Test
    fun `should return all patients`() {
        saveOnePatient()

        val response = restTemplate.getForEntity(
            getRootUrl(),
            List::class.java
        )

        assertEquals(200, response.statusCode.value())
        assertNotNull(response.body)
        assertEquals(1, response.body?.size)
    }

    @Test
    fun `should return single patient by id`() {
        saveOnePatient()

        val response = restTemplate.getForEntity(
            getRootUrl() + "/$defaultPatientId",
            Patient::class.java
        )

        assertEquals(200, response.statusCode.value())
        assertNotNull(response.body)
        assertEquals(defaultPatientId, response.body?.id)
    }
}