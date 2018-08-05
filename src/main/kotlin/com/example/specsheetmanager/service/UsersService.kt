package com.example.specsheetmanager.service

import com.example.specsheetmanager.domain.User
import com.example.specsheetmanager.repository.UserRepository
import com.example.specsheetmanager.web.form.CreateUserForm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UsersService(
        @Autowired
        private val userRepository: UserRepository
) {

    fun createUser(form: CreateUserForm): Boolean = try {
        userRepository.save(User.convertFromCreateForm(form))
        true

    } catch (e: Exception) {
        e.printStackTrace()
        false
    }

}