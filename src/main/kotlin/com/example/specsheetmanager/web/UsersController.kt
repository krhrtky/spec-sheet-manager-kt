package com.example.specsheetmanager.web

import com.example.specsheetmanager.web.form.CreateUserForm
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("users")
class UsersController {

    @ModelAttribute
    fun setUpCreateUserForm(): CreateUserForm {
        return CreateUserForm()
    }

    @RequestMapping("/new")
    fun new(): String {
        return "users/new"
    }

    @PostMapping("/create")
    fun createUser(
            @Validated
            form: CreateUserForm,
            bindingResult: BindingResult,
            model: Model
    ): String {

        if (bindingResult.hasErrors()) return "users/new"

        return "./top"
    }
}