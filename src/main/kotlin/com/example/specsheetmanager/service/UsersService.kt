package com.example.specsheetmanager.service

import com.example.specsheetmanager.domain.convertToUserFromForm
import com.example.specsheetmanager.repository.UserRepository
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
            userRepository.create(convertToUserFromForm(form))
            true
        } catch (e: Exception) {
            false
        }
    }
}