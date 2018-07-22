package com.example.specsheetmanager.util

import com.example.specsheetmanager.domain.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails


fun getSessionUser(): User? {

    val authentication = SecurityContextHolder
            .getContext()
            .authentication

    return  if (authentication.principal is UserDetails) {
        (authentication.principal as User)
    } else {
        null
    }
}

