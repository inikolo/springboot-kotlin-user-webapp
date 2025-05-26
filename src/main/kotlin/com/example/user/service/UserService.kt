package com.example.user.service

import com.example.user.dto.CreateUserRequest
import com.example.user.dto.GetUsersResponse
import com.example.user.dto.toDTO
import com.example.user.exception.DuplicateEmailException
import com.example.user.model.User
import com.example.user.repository.UserRepository
import org.springframework.data.domain.PageRequest
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

    fun getUsersByNameStartingWith(namePrefix: String, pageNumber: Int, pageSize: Int): GetUsersResponse {

        require(pageNumber >= 0) { "Page number must be non-negative, but is was $pageNumber" }
        require(pageSize >= 1) { "Page size must be positive, but is was $pageSize" }

        val pageRequest = PageRequest.of(pageNumber, pageSize)
        val userDTOList = userRepository.findAllByNameStartingWithIgnoreCase(namePrefix, pageRequest)
            .map(User::toDTO)
        val totalNumOfUsers = userRepository.count()

        return GetUsersResponse(userDTOList, totalNumOfUsers)
    }
}