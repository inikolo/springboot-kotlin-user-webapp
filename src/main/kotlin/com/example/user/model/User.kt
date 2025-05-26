package com.example.user.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(var name: String, var email: String, var password: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0
}