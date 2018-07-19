package com.example.specsheetmanager.domain

import com.example.specsheetmanager.web.form.CreateUserForm
import org.springframework.util.DigestUtils

data class User(
        val id: Int?,
        val name: String,
        val email: String,
        var password: String,
        var projectList: List<Project> = emptyList()
)

fun convertToUserFromForm(form: CreateUserForm): User {
    val (name, email, password) = form

    val digestPassword = if (password.isNullOrBlank())
        throw NullPointerException()
    else DigestUtils.md5DigestAsHex(password!!.toByteArray())

    return User(
            null ,
            name?: "",
            email?: "",
            digestPassword
    )
}
