package com.example.user.service

import com.example.user.dto.CreateUserRequest
import com.example.user.exception.DuplicateEmailException
import com.example.user.model.User
import com.example.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun createUser(createUserRequest: CreateUserRequest) {

        with(createUserRequest) {
            userRepository.findByEmailIgnoreCase(email)?.let {
                throw DuplicateEmailException("Duplicate e-mail: $email")
            }

            //TODO: encrypt password here
            userRepository.save(User(name, email, password))
        }
    }
}