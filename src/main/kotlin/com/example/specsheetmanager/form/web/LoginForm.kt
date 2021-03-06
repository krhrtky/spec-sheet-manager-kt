package com.example.specsheetmanager.form.web

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class LoginForm(
  @get:NotBlank
  var name: String? = null,
  @get:NotBlank
  @get:Email
  var email: String? = null,
  @get:Size(min = 5)
  var password: String? = null,
  @get:Size(min = 5)
  var confirmationPassword: String? = null
)
