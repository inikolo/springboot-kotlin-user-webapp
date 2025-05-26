package com.example.user.controller

import com.example.user.dto.CreateUserRequest
import com.example.user.dto.GetUsersResponse
import com.example.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @PostMapping
    fun createUser(@RequestBody createUserRequest: CreateUserRequest): ResponseEntity<Void> {
        userService.createUser(createUserRequest)
        return ResponseEntity.accepted().build()
    }

    @GetMapping
    fun getUsersByNameStartingWith(
        @RequestParam(defaultValue = "") query: String,
        @RequestParam(defaultValue = "10") limit: Int
    ): ResponseEntity<GetUsersResponse> {
        return ResponseEntity.ok(userService.getUsersByNameStartingWith(query,0, limit))
    }
}