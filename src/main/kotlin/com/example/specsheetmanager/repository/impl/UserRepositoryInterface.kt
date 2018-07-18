package com.example.specsheetmanager.repository.impl

import com.example.specsheetmanager.domain.User
import org.springframework.stereotype.Repository

@Repository
interface UserRepository {

    fun create(user: User)
}