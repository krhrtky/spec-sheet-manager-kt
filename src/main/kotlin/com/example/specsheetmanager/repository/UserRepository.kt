package com.example.specsheetmanager.repository

import com.example.specsheetmanager.domain.User
import org.springframework.stereotype.Repository

@Repository
interface UserRepository {

    fun create(user: User)

    fun findByEmail(email: String): User

    fun findByEmailAndPassword(email: String, digestPassword: String): User

}