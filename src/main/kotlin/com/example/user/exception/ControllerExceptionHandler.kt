package com.example.user.exception

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ControllerExceptionHandler {

    private val logger = LoggerFactory.getLogger(ControllerExceptionHandler::class.java)
    var errorResponse: MutableMap<String, String?> = HashMap()

    @ExceptionHandler(DuplicateEmailException::class)
    fun handleDuplicateEmailException(ex: DuplicateEmailException): ResponseEntity<Map<String, String?>> {
        logger.info("DuplicateEmailException occurred: ${ex.message}")
        errorResponse["error"] = ex.message
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }
}