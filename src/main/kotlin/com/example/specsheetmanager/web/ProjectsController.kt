package com.example.specsheetmanager.web

import com.example.specsheetmanager.domain.Project
import com.example.specsheetmanager.service.ProjectService
import com.example.specsheetmanager.util.getSessionUser
import com.example.specsheetmanager.web.form.AddProjectForm
import com.example.specsheetmanager.web.form.EditProjectForm
import org.springframework.beans.factory.annotation.Autowired
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
    fun setUpProjectForm(): AddProjectForm {
        return AddProjectForm()
    }

    @RequestMapping("/new")
    fun new(): String {

        return "projects/new"
    }

    @PostMapping("/add")
    fun addProject(
            @Validated
            form: AddProjectForm,
            bindingResult: BindingResult,
            model: Model
    ): String {

        if(bindingResult.hasErrors()) return "projects/new"

        val user = getSessionUser() ?: throw IllegalAccessException("session情報にユーザーがありません。")

        return if(projectService.insertProject(form, user.id!!)) showProjects(model) else "project/new"
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

    @PostMapping("/{id}/edit")
    fun edit(
            @Validated
            form: EditProjectForm,
            bindingResult: BindingResult,
            model: Model
    ): String {

        val user = getSessionUser() ?: throw IllegalAccessException("session情報にユーザーがありません。")

        if(bindingResult.hasErrors()) return "/projects/edit"

        return if(projectService.editProject(form, user.id!!)) showProjects(model) else "project/edit"
    }
}