package com.example.user.dto

data class GetUsersResponse(
    val users: List<UserDTO>,
    val totalCount: Long,
)