package com.example.specsheetmanager.service

import com.example.specsheetmanager.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

open class LoginService(
        @Autowired
        private val userRepository: UserRepository
): UserDetailsService {

        override fun loadUserByUsername(username: String): UserDetails? {
            return try { userRepository.findByEmail(username) } catch (e: Exception) { null }
        }
}