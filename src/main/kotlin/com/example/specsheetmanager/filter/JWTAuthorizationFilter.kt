package com.example.specsheetmanager.filter

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JWTAuthorizationFilter(
  private val authenticationManager: AuthenticationManager
): BasicAuthenticationFilter(authenticationManager) {

  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
    //TODO: ヘッダー外出し
    val header =  request.getHeader("SSM-TOKEN")?: ""

    //TODO: prefix外出し
    if (header.isNotBlank() || header.startsWith("ssm-token:")) {
      val usernamePasswordAuthenticationToken = getAuthentication(request)
      SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
    }

    chain.doFilter(request, response)
  }

  private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
    //TODO: ヘッダー外出し
    val tokenWithPadding =  request.getHeader("SSM-TOKEN")?: ""
    if (tokenWithPadding != null) {

      val key = Keys.secretKeyFor(SignatureAlgorithm.HS256)
      //TODO: prefix外出し
      val token = tokenWithPadding.replace("ssm-token:", "")

      //TODO: キー外部ファイル化
      val user = try {
        // parse the token.
        Jwts
          .parser()
          .setSigningKey(Keys.hmacShaKeyFor("secretsecretsecretsecretsecretsecretsecret".toByteArray()))
          .parseClaimsJws(token)
          .body
          .subject

      } catch (e: Exception) {
        e.printStackTrace()
        null
      }



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
