package com.example.specsheetmanager.web

import com.example.specsheetmanager.web.form.LoginForm
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class IndexController {

    @ModelAttribute
    fun setUpLoginForm(): LoginForm {
        return LoginForm()
    }

    @RequestMapping("/")
    fun index(): String {
        return "index"
    }

    @RequestMapping("login")
    fun login(): String {
        return "login"
    }

    @RequestMapping("top")
    fun top(): String {
        return "top"
    }
}

