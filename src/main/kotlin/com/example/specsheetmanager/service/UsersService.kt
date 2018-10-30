package com.example.specsheetmanager.service

import com.example.specsheetmanager.domain.User
import com.example.specsheetmanager.form.web.CreateUserForm
import com.example.specsheetmanager.form.web.EditUserForm
import com.example.specsheetmanager.repository.UserRepository
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class UsersService(
  @Autowired
  private val userRepository: UserRepository
) {

  fun createUser(form: CreateUserForm) = try {
    userRepository.save(User.convertFromCreateForm(form))
    true

  } catch (e: Exception) {
    e.printStackTrace()
    false
  }

  fun updateUser(form: EditUserForm, userId: Int)= try {
    val currentUser = userRepository
      .findById(userId)
      .orElse(null)
      ?: throw EntityNotFoundException()
    BeanUtils.copyProperties(form, currentUser)
    userRepository.save(currentUser)
    true

  } catch (e: Exception) {
    e.printStackTrace()
    false

  }
}