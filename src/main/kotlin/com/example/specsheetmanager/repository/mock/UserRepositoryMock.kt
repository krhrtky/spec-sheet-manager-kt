package com.example.specsheetmanager.repository.mock

import com.example.specsheetmanager.domain.User
import com.example.specsheetmanager.repository.UserRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryMock: UserRepository {

    override fun create(user: User) {

    }
}