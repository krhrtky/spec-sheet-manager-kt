package com.example.specsheetmanager.filter

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.util.ArrayList
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder


class JWTAuthorizationFilter(
  private val authenticationManager: AuthenticationManager
): BasicAuthenticationFilter(authenticationManager) {

  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
    val header =  request.getHeader("SSM-TOKEN")?: ""

    if (header.isNotBlank() || header.startsWith("ssm-token:")) {
      val usernamePasswordAuthenticationToken = getAuthentication(request)
      SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
    }

    chain.doFilter(request, response)
  }

  private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
    val token =  request.getHeader("SSM-TOKEN")?: ""
    if (token != null) {

      val key = Keys.secretKeyFor(SignatureAlgorithm.HS256)

      // parse the token.
      val user = Jwts.parser()
        .setSigningKey(key)
        .parseClaimsJws(token.replace("ssh-token:", ""))
        .getBody()
        .getSubject()

      return if (user != null) {
        UsernamePasswordAuthenticationToken(user, null, ArrayList<GrantedAuthority>())
      } else null
    }
    return null
  }


  override fun getAuthenticationManager(): AuthenticationManager {
    return super.getAuthenticationManager()
  }
}
