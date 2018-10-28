package com.example.specsheetmanager.controller.web

import com.example.specsheetmanager.controller.web.form.LoginForm
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class IndexController {

  @ModelAttribute
  fun setUpLoginForm(): LoginForm = LoginForm()

  @RequestMapping("/")
  fun index(): String = "index"

  @RequestMapping("login")
  fun login(): String = "login"

  @RequestMapping("top")
  fun top(): String = "top"
}

