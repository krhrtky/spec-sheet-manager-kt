package com.example.specsheetmanager.service

import com.example.specsheetmanager.repository.impl.UserRepository
import com.example.specsheetmanager.util.convertToUser
import com.example.specsheetmanager.web.form.CreateUserForm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UsersService(
        @Autowired
        val userRepository: UserRepository
) {

    fun createUser(form: CreateUserForm): Boolean {

        return try {
            userRepository.create(convertToUser(form))
            true
        } catch (e: Exception) {
            false
        }
    }
}