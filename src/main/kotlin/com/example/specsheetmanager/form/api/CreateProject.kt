package com.example.specsheetmanager.form.api

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateProject(
  @get:NotBlank
  var name: String? = null,
  @NotNull
  @JsonFormat(pattern = "yyyy-MM", timezone = "Asia/Tokyo")
  var startDate: Date? = null,
  @NotNull
  @JsonFormat(pattern = "yyyy-MM", timezone = "Asia/Tokyo")
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
