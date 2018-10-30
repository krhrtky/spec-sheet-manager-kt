package com.example.specsheetmanager.controller.api

import com.example.specsheetmanager.form.api.ViewProject
import com.example.specsheetmanager.service.ProjectConverter
import com.example.specsheetmanager.service.ProjectService
import com.example.specsheetmanager.util.getSessionUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
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

    return ProjectConverter.convertForViewWith(projectService.findProjectList(user!!.id!!))
  }

  //TODO: ステータスコードを実行結果で変更できるようにする.
  @PutMapping("/{id}/edit")
  fun edit(
    @RequestBody
    @Validated
    form: ViewProject
  ) {

    val user = getSessionUser()
    val project = ProjectConverter.convertForEntityWith(form, user!!.id!!)

    projectService.editProject(project)
  }
}