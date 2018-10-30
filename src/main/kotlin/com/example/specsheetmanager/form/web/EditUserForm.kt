package com.example.specsheetmanager.form.web

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class EditUserForm(
  @get:NotBlank
  var name: String? = null,
  @get:NotBlank
  @get:Email
  var email: String? = null
)
