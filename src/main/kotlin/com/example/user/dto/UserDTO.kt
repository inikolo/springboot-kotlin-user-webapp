package com.example.user.dto

import com.example.user.model.User

data class UserDTO(val email: String, val name: String)

fun User.toDTO() = UserDTO(
    email = email,
    name = name
)