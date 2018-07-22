package com.example.specsheetmanager.domain

import com.example.specsheetmanager.web.form.CreateUserForm
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.util.DigestUtils

data class User(
        val id: Int?,
        val name: String,
        val email: String,
        val digestPassword: String,
        var projectList: List<Project> = emptyList(),
        val enabled: Boolean = true,
        val roleType: String = "USER"
): UserDetails {

        override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
            return AuthorityUtils.createAuthorityList(this.roleType)
        }

        override fun isEnabled(): Boolean {
            return this.enabled
        }

        override fun getUsername(): String? {
            return this.email
        }

        override fun isCredentialsNonExpired(): Boolean {
            return true
        }

        override fun getPassword(): String? {
            return this.digestPassword
        }

        override fun isAccountNonExpired(): Boolean {
            return true
        }

        override fun isAccountNonLocked(): Boolean {
            return true
        }
}

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
