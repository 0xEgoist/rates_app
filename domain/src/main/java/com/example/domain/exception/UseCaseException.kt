package com.example.domain.exception

class UseCaseException(private val useCaseException: UseCaseExceptions) : RuntimeException() {
    override val message = useCaseException.message
    override fun toString() = "Use case exception: \"${useCaseException.message}\""
}