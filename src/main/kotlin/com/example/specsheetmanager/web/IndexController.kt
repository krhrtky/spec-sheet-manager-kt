package com.example.specsheetmanager.web

import com.example.specsheetmanager.web.form.LoginForm
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

