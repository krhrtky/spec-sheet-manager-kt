package com.example.specsheetmanager.filter

import com.example.specsheetmanager.domain.User
import com.example.specsheetmanager.web.form.LoginForm
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(
  private val authenticationManager: AuthenticationManager,
  private val passwordEncoder: PasswordEncoder
) : UsernamePasswordAuthenticationFilter(
) {

  init {
    setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher("/api/auth", "POST"))
    usernameParameter = "email"
  }

  override fun getAuthenticationManager(): AuthenticationManager {
    return this.authenticationManager
  }

  override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
    val loginForm = ObjectMapper()
      .readValue(
        request?.inputStream,
        LoginForm::class.java)

    return this.authenticationManager
      .authenticate(
        UsernamePasswordAuthenticationToken(
          loginForm.email,
          loginForm.name,
          mutableListOf()
        )
      )
  }

  override fun successfulAuthentication(
    request: HttpServletRequest?,
    response: HttpServletResponse?,
    chain: FilterChain?,
    authResult: Authentication?) {

    val user = authResult!!.principal as User
    val expiredDate = Date(System.currentTimeMillis() + 604800000/*1週間 */)
    val key = Keys.secretKeyFor(SignatureAlgorithm.HS256)

    val token = Jwts
      .builder()
      .setSubject(user.username)
      ?.setExpiration(expiredDate)
      ?.signWith(key)
      ?.compact()

    response?.addHeader("SSM-TOKEN", "ssh-token:$token")
  }
}
