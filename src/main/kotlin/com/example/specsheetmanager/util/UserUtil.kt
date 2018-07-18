package com.example.specsheetmanager.util

import com.example.specsheetmanager.domain.User
import com.example.specsheetmanager.web.form.CreateUserForm

fun convertToUser(form: CreateUserForm): User {
    val (name, email, password) = form

    return User(
            null ,
            name?: "",
            email?: "",
            password?: ""
    )
}

