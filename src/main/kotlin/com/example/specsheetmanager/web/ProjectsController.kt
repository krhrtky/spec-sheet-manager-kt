package com.example.specsheetmanager.web

import com.example.specsheetmanager.domain.User
import com.example.specsheetmanager.service.ProjectService
import com.example.specsheetmanager.util.getSessionUser
import com.example.specsheetmanager.web.form.AddProjectForm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("projects")
class ProjectsController(
        @Autowired
        private val projectService: ProjectService
) {

    @ModelAttribute
    fun setUpProjectForm(): AddProjectForm {
        return AddProjectForm()
    }

    @RequestMapping("/new")
    fun new(): String {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication

        val userUuid = if (authentication.principal is UserDetails) {
            (authentication.principal as User).id
        } else {
            null
        }

        return "projects/new"
    }

    @PostMapping("add")
    fun addProject(
            @Validated
            form: AddProjectForm,
            bindingResult: BindingResult,
            model: Model
    ): String {

        if(bindingResult.hasErrors()) return "projects/new"

        val user = getSessionUser() ?: throw IllegalAccessException("session情報にユーザーがありません。")

        return if(projectService.insertProject(form, user.id!!)) "top" else "project/new"
    }

    @GetMapping("/")
    fun showProjects(model: Model): String {
        val user = getSessionUser() ?: throw IllegalAccessException("session情報にユーザーがありません。")

        model.addAttribute(projectService.findProjectlist(user.id!!))

        return "/projects/list"
    }
}