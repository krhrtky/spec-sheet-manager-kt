package com.example.specsheetmanager.web

import com.example.specsheetmanager.service.ProjectService
import com.example.specsheetmanager.web.form.AddProjectForm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
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
        // TODO: userId をsessionから取得する
        return if(projectService.insertProject(form, 1)) "top" else "project/new"

    }
}