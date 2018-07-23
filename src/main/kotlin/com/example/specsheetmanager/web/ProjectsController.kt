package com.example.specsheetmanager.web

import com.example.specsheetmanager.domain.User
import com.example.specsheetmanager.service.ProjectService
import com.example.specsheetmanager.util.getSessionUser
import com.example.specsheetmanager.web.form.ProjectForm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("projects")
class ProjectsController(
        @Autowired
        private val projectService: ProjectService
) {

    @ModelAttribute
    fun setUpProjectForm(): ProjectForm {
        return ProjectForm()
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

    @PostMapping("/add")
    fun addProject(
            @Validated
            form: ProjectForm,
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

        model.addAttribute(projectService.findProjectList(user.id!!))

        return "/projects/list"
    }

    @GetMapping("/{id}")
    fun show(@PathVariable id: Int, model: Model): String {

        val user = getSessionUser() ?: throw IllegalAccessException("session情報にユーザーがありません。")

        model.addAttribute(projectService.findByUserIdProjectId(user.id!!, id))

        return "/projects/detail"
    }

    @GetMapping("/{id}/edit")
    fun editPage(@PathVariable id: Int, model: Model): String {

        val user = getSessionUser() ?: throw IllegalAccessException("session情報にユーザーがありません。")

        model.addAttribute(projectService.findByUserIdProjectId(user.id!!, id))

        return "/projects/edit"
    }

    @PostMapping("/edit")
    fun edit(
            @Validated
            form: ProjectForm,
            bindingResult: BindingResult,
            model: Model
    ): String {

        if(bindingResult.hasErrors()) return "/projects/edit"
        val user = getSessionUser() ?: throw IllegalAccessException("session情報にユーザーがありません。")

        return if(projectService.editProject(form, user.id!!)) "/projects/list" else "project/edit"
    }
}