package com.example.user.service

import com.example.user.dto.CreateUserRequest
import com.example.user.dto.GetUsersResponse
import com.example.user.dto.toDTO
import com.example.user.exception.DuplicateEmailException
import com.example.user.model.User
import com.example.user.repository.UserRepository
import org.springframework.data.domain.PageRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) {

    fun createUser(createUserRequest: CreateUserRequest) {

        with(createUserRequest) {
            userRepository.findByEmailIgnoreCase(email)?.let {
                throw DuplicateEmailException("Duplicate e-mail: $email")
            }

            val encodedPassword = passwordEncoder.encode(password)
            userRepository.save(User(name, email, encodedPassword))
        }
    }

    fun getUsersByNameStartingWith(namePrefix: String, pageNumber: Int = 0, pageSize: Int): GetUsersResponse {

        require(pageNumber >= 0) { "Page number must be non-negative, but it was $pageNumber" }
        require(pageSize >= 1) { "Page size must be positive, but it was $pageSize" }

        val pageRequest = PageRequest.of(pageNumber, pageSize)
        val userDTOList = userRepository.findAllByNameStartingWithIgnoreCase(namePrefix, pageRequest)
            .map(User::toDTO)
        val totalNumOfUsers = userRepository.count()

        return GetUsersResponse(userDTOList, totalNumOfUsers)
    }
}