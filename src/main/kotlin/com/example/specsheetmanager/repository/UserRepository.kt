package com.example.specsheetmanager.repository

import com.example.specsheetmanager.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Int> {

    fun findByEmail(email: String): User

    fun findByEmailAndDigestPassword(email: String, digestPassword: String): User

}