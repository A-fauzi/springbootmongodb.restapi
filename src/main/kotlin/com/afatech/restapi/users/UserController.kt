package com.afatech.restapi.users

import com.afatech.restapi.Utils
import com.afatech.restapi.Utils.formattedDate
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
@RequestMapping("/users")
class UserController(private val userRepository: UserRepository) {

    @GetMapping
    fun getAllUser(): ResponseEntity<UserAllResponse> {
        val user = userRepository.findAll()
        val responseNotEmpty = UserAllResponse("Success Get Data Uer", user)
        val responseEmpty = UserAllResponse("Data user is empty", user)

        return if (user.isNotEmpty()) ResponseEntity(responseNotEmpty, HttpStatus.OK)
        else ResponseEntity(responseEmpty, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable("id") id: String): ResponseEntity<UserResponse> {
        return if (userRepository.existsById(id)) {
            val user = userRepository.findOneById(id)
            val response = UserResponse("User dengan id $id berhasil ditemukan", user)
            ResponseEntity(response, HttpStatus.OK)
        } else {
            val responseNot = UserResponse("User dengan id $id tidak ditemukan, periksa kembali id user")
            ResponseEntity(responseNot, HttpStatus.NOT_FOUND)
        }

    }

    @PostMapping
    fun createUser(@RequestBody request: UserRequest): ResponseEntity<UserResponse> {
        val user = userRepository.save(
            User(
                id = request.id,
                name = request.name,
                phone = request.phone,
                email = request.email,
                photoUrl = request.photoUrl,
                gender = request.gender,
                title = request.title,
                description = request.description,
                pathStorageProfile = request.pathStorageProfile,
                followers = request.followers,
                following = request.following
            )
        )
        val response = UserResponse("Data User Created", user)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateUser(@RequestBody request: UserRequest, @PathVariable("id") id: String): ResponseEntity<UserResponse> {
        if (userRepository.existsById(id)) {
            val user = userRepository.findOneById(id)
            val updateUser = userRepository.save(
                User(
                    id = user.id,
                    name = request.name,
                    phone = request.phone,
                    email = request.email,
                    photoUrl = request.photoUrl,
                    createdDate = user.createdDate,
                    modifiedDate = formattedDate,
                    gender = request.gender,
                    title = request.title,
                    description = request.description,
                    pathStorageProfile = request.pathStorageProfile,
                    followers = request.followers,
                    following = request.following
                )
            )
            val response = UserResponse("Data User Updated", updateUser)
            return ResponseEntity(response, HttpStatus.CREATED)
        } else {
            val response = UserResponse("User dengan id $id tidak ditemukan")
            return ResponseEntity(response, HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable("id") id: String): ResponseEntity<UserResponse> {
        return if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
            val response = UserResponse("Data Success Deleted")
            ResponseEntity(response, HttpStatus.OK)
        } else {
            val response = UserResponse("User dengan id $id tidak di temukan")
            ResponseEntity(response, HttpStatus.NOT_FOUND)
        }
    }
}