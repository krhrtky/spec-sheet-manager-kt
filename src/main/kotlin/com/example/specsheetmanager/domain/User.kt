package com.example.specsheetmanager.domain

import com.example.specsheetmanager.web.form.CreateUserForm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.util.DigestUtils
import javax.persistence.*
import javax.persistence.FetchType



@Entity
@Table
data class User(
        @Id
        @GeneratedValue()
        @Column(name = "id")
        var id: Int? = null,
        @Column(name = "name")
        var name: String = "",
        @Column(name = "email")
        var email: String = "",
        @Column(name = "digest_password")
        var digestPassword: String = "",
        @OneToMany(fetch = FetchType.EAGER)
        @JoinColumn(name = "user_id")
        var projectList: List<Project> = emptyList(),
        @Column(name = "enabled")
        var enabled: Boolean = true,
        @Column(name = "role_type")
        var roleType: String = "USER"
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

        private var passwordEncoder: PasswordEncoder = BCryptPasswordEncoder();
        fun convertFromCreateForm(form: CreateUserForm): User {

            val (name, email, password) = form

            val digestPassword = if (password.isNullOrBlank())
                throw NullPointerException()
            else passwordEncoder.encode(password)

            return User(
                    null ,
                    name?: "",
                    email?: "",
                    digestPassword
            )

        }
    }
}

