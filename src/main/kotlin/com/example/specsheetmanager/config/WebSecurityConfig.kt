package com.example.specsheetmanager.config

import com.example.specsheetmanager.filter.CORSFilter
import com.example.specsheetmanager.filter.JWTAuthenticationFilter
import com.example.specsheetmanager.filter.JWTAuthorizationFilter
import com.example.specsheetmanager.repository.UserRepository
import com.example.specsheetmanager.service.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
class WebSecurityConfig(
  @Autowired
  private val userRepository: UserRepository,
  @Autowired
  private val passwordEncoder: PasswordEncoder,
  @Autowired
  private val userDetailsService: UserDetailsService
): WebSecurityConfigurerAdapter() {

  override fun configure(web: WebSecurity?) {
    web
      ?.ignoring()
      ?.antMatchers(
        "/**/favicon.ico",
        "/img/**",
        "/css/**",
        "/js/**",
        "/index.html"
      )
  }

  override fun configure(http: HttpSecurity) {
    http
      .cors()
      .and().authorizeRequests()
      .antMatchers(
        "/",
        "/api/auth",
        "/api/login",
        "/login",
        "/users/new",
        "/users/create",
        "/authenticate"
      )
      .permitAll()
      .anyRequest()
      .authenticated()
      .and()
      .logout()
      .and()
      .csrf()
      .disable()
      .addFilterBefore(CORSFilter(), JWTAuthenticationFilter::class.java)
      .addFilter(JWTAuthenticationFilter(authenticationManager(), passwordEncoder))
      .addFilter(JWTAuthorizationFilter(authenticationManager()))
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)


    http
      .formLogin()
      .loginProcessingUrl("/authenticate")
      .loginPage("/login")
      .failureUrl("/login")
      .defaultSuccessUrl("/top", false)
      .usernameParameter("email")
      .passwordParameter("password")
      .and()

    http
      .logout()
      .logoutRequestMatcher(AntPathRequestMatcher("/logout**"))
      .logoutSuccessUrl("/")
  }

  @Throws(Exception::class)
  fun configureAuth(auth: AuthenticationManagerBuilder) {
    auth
      .userDetailsService(userDetailsService)
      .passwordEncoder(passwordEncoder)
  }

  override fun configure(auth: AuthenticationManagerBuilder) {
    auth
      .userDetailsService(LoginService(userRepository))
      .passwordEncoder(passwordEncoder)
  }


  /** //   * CORS設定
   *
   * @return CORS設定
   */
  private fun corsConfigurationSource(): CorsConfigurationSource {
    val corsConfiguration = CorsConfiguration()
    corsConfiguration.addAllowedMethod(CorsConfiguration.ALL)
    corsConfiguration.addAllowedHeader(CorsConfiguration.ALL)
    corsConfiguration.addAllowedOrigin("http://localhost:8081")
    corsConfiguration.setAllowCredentials(true)

    val corsConfigurationSource: UrlBasedCorsConfigurationSource = UrlBasedCorsConfigurationSource()
    corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration)

    return corsConfigurationSource
  }

  @Configuration
  protected class AuthenticationConfiguration(
    @Autowired
    private val userDetailsService: UserDetailsService
  ) : GlobalAuthenticationConfigurerAdapter() {

    @Throws(Exception::class)
    override fun init(auth: AuthenticationManagerBuilder) {
      auth
        .userDetailsService<UserDetailsService>(userDetailsService)
        .passwordEncoder(BCryptPasswordEncoder())

    }
  }
}
