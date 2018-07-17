package com.example.specsheetmanager.web.form

import javax.validation.constraints.Email
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class CreateUserForm(
        @get:NotBlank
        var name: String? = null,
        @get:NotBlank
        @get:Email
        var email: String? = null,
        @get:Min(4)
        var password: String? = null,
        @get:Min(4)
        var confirmationPassword: String? = null
)
