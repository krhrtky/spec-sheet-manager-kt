package com.example.specsheetmanager.web

import com.example.specsheetmanager.service.UsersService
import com.example.specsheetmanager.util.getSessionUser
import com.example.specsheetmanager.web.form.CreateUserForm
import com.example.specsheetmanager.web.form.EditUserForm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
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

    @ModelAttribute
    fun setUpEditUserForm(): EditUserForm = EditUserForm()

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

    @GetMapping("/edit")
    fun editPage(model: Model): String {

        val user = getSessionUser() ?: throw IllegalAccessException("session情報にユーザーがありません。")

        model.addAttribute("user", user)

        return "/users/edit"
    }

    @PostMapping("/edit")
    fun editUser(
            @Validated
            form: EditUserForm,
            bindingResult: BindingResult,
            model: Model
    ): String {

        val user = getSessionUser() ?: throw IllegalAccessException("session情報にユーザーがありません。")

        if(bindingResult.hasErrors()) return "users/edit"

        return if (usersService.updateUser(form, user.id!!)) "redirect:/top" else "/users/edit"
    }

}