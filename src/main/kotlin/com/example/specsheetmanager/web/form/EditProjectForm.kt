package com.example.specsheetmanager.web.form

import org.springframework.format.annotation.DateTimeFormat
import java.util.Date
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class EditProjectForm(
  @get:NotNull
  var id: Int? = null,
  @get:NotBlank
  var name: String? = null,
  @NotNull
  @DateTimeFormat(pattern = "yyyy/MM/dd")
  var startDate: Date? = null,
  @NotNull
  var endDate: Date? = null,
  @get:NotBlank
  var language: String? = null,
  @get:NotBlank
  var framework: String? = null,
  @get:NotBlank
  var middleware: String? = null,
  @get:NotBlank
  var about: String? = null
)

