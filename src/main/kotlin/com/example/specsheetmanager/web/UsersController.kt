package com.example.specsheetmanager.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("users")
class UsersController {

    @GetMapping("/new")
    fun new(): String {
        return "users/new"
    }

    @PostMapping("/create")
    fun createUser(): String {

        return "./top"
    }
}