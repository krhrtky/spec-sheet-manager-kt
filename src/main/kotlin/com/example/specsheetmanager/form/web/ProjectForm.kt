package com.example.specsheetmanager.form.web

import java.util.*
import javax.validation.constraints.NotBlank

data class ProjectForm(

  @NotBlank
  var id: Int? = null,
  @NotBlank
  var name: String? = null,
  @NotBlank
  var startDate: Date? = null,
  @NotBlank
  var endDate: Date? = null,
  @NotBlank
  var language: String? = null,
  @NotBlank
  var framework: String? = null,
  @NotBlank
  var middleware: String? = null,
  @NotBlank
  var about: String? = null
)
