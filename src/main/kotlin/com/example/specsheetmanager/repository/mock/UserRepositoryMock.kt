package com.example.specsheetmanager.repository.mock

import com.example.specsheetmanager.domain.User
import com.example.specsheetmanager.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryMock(
        @Autowired
        private val passwordEncoder: PasswordEncoder
): UserRepository {

    override fun create(user: User) {

    }

    override fun findByEmail(email: String): User {
       return when(email) {
           "test1@email.com" -> User(
                1,
                "test_user1",
                "test1@email.com",
                passwordEncoder.encode("1234567")
           )
           "test2@email.com" -> User(
                   2,
                   "test_user2",
                   "test2@email.com",
                   passwordEncoder.encode("1234567")
           )
           else -> User(
                   0,
                   "test_user0",
                   "test0@email.com",
                   passwordEncoder.encode("1234567")
           )
       }
    }

    @Throws(Exception::class)
    override fun findByEmailAndPassword(email: String, digestPassword: String): User {
        return User(
                1,
                "test_user",
                "test@email.com",
            ""
        )
    }

}