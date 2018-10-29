package com.example.specsheetmanager.controller.api

import com.example.specsheetmanager.controller.api.form.ViewProject
import com.example.specsheetmanager.service.ProjectConverterForView
import com.example.specsheetmanager.service.ProjectService
import com.example.specsheetmanager.util.getSessionUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/projects")
class ProjectsRestController(
  @Autowired
  private val projectService: ProjectService
) {

  @GetMapping("")
  fun getAllProject(): List<ViewProject> {
    val user = getSessionUser()

    return ProjectConverterForView.convertWith(projectService.findProjectList(user!!.id!!))
  }

  @PostMapping("/{id}/edit")
  fun edit(
    @RequestBody
    @Validated
    form: ViewProject
  ): ViewProject {

    return form
  }
}