package com.example.specsheetmanager.domain

import com.example.specsheetmanager.web.form.CreateUserForm
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.util.DigestUtils
import javax.persistence.*

@Entity
@Table
data class User(
        @Id
        @GeneratedValue()
        @Column(name = "id")
        val id: Int?,
        @Column(name = "name")
        val name: String,
        @Column(name = "email")
        val email: String,
        @Column(name = "password")
        val digestPassword: String,
        @OneToMany(mappedBy = "project")
        @JoinColumn(name="user_id")
        var projectList: List<Project> = emptyList(),
        @Column(name = "enabled")
        val enabled: Boolean = true,
        @Column(name = "role_type")
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

    companion object {
        fun convertFromCreateForm(form: CreateUserForm): User {
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
    }
}

