package com.example.specsheetmanager.web

import com.example.specsheetmanager.service.UsersService
import com.example.specsheetmanager.web.form.CreateUserForm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("users")
class UsersController(
        @Autowired
        val usersService: UsersService
) {

    @ModelAttribute
    fun setUpCreateUserForm(): CreateUserForm = CreateUserForm()

    @RequestMapping("/new")
    fun new(): String = "users/new"

    @PostMapping("/create")
    fun createUser(
            @Validated
            form: CreateUserForm,
            bindingResult: BindingResult,
            model: Model
    ): String {

        if (bindingResult.hasErrors()) return "users/new"

        return if (usersService.createUser(form)) "./top" else "users/new"
    }
}