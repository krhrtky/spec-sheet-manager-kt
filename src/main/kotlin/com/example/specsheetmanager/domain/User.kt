package com.example.specsheetmanager.domain

import com.example.specsheetmanager.form.web.CreateUserForm
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils.createAuthorityList
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table
data class User(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  override fun getAuthorities(): MutableCollection<out GrantedAuthority>? = createAuthorityList(this.roleType)

  override fun isEnabled() = this.enabled

  override fun getUsername() = this.email

  override fun isCredentialsNonExpired() = true

  override fun getPassword() = this.digestPassword

  override fun isAccountNonExpired() = true

  override fun isAccountNonLocked() = true

  companion object {

    private var passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()
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

