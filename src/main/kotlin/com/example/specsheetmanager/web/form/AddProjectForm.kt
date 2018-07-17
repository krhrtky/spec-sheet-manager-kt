package com.example.specsheetmanager.web.form

import java.sql.Date
import javax.validation.constraints.NotBlank

data class AddProjectForm(
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