package com.example.specsheetmanager.controller.web

import com.example.specsheetmanager.form.web.LoginForm
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class IndexController {

  @ModelAttribute
  fun setUpLoginForm() = LoginForm()

  @RequestMapping("/")
  fun index() = "index"

  @RequestMapping("login")
  fun login() = "login"

  @RequestMapping("top")
  fun top() = "top"
}

