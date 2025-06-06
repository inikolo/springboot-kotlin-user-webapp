package com.example.user.repository

import com.example.user.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByEmailIgnoreCase(email: String): User?

    fun findAllByNameStartingWithIgnoreCase(name: String, pageable: Pageable): Page<User>
}