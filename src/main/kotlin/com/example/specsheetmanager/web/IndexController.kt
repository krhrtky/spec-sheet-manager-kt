package com.example.specsheetmanager.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class IndexController {

    @RequestMapping("/")
    fun index(): String {
        return "index"
    }

    @RequestMapping("login")
    fun login(): String {
        return "login"
    }

    @PostMapping("authenticate")
    fun authenticate(): String {
        return "top"
    }
}
