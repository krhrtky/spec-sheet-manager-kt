package com.example.specsheetmanager.service

import com.example.specsheetmanager.domain.User
import com.example.specsheetmanager.repository.UserRepository
import com.example.specsheetmanager.web.form.CreateUserForm
import com.example.specsheetmanager.web.form.EditUserForm
import com.fasterxml.jackson.databind.util.BeanUtil
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityNotFoundException

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

    fun updateUser(form: EditUserForm, userId: Int): Boolean = try {
        val currentUser = userRepository.findById(userId).orElse(null) ?: throw EntityNotFoundException()
        BeanUtils.copyProperties(currentUser, form)
        userRepository.save(currentUser)
        true

    } catch (e: Exception) {
        e.printStackTrace()
        false

    }
}