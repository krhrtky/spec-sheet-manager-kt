package com.example.specsheetmanager.controller.api

import com.example.specsheetmanager.form.api.CreateProject
import com.example.specsheetmanager.form.api.ViewProject
import com.example.specsheetmanager.service.ProjectConverter
import com.example.specsheetmanager.service.ProjectService
import com.example.specsheetmanager.util.getSessionUser
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/projects")
class ProjectsRestController(
  private val projectService: ProjectService,
  private val projectConverter: ProjectConverter
) {

  @GetMapping("")
  fun getAllProject(): List<ViewProject> {
    val user = getSessionUser()

    return projectConverter.convertForViewWith(projectService.findProjectList(user!!.id!!))
  }

  @PostMapping("/create")
  fun createProject(
    @RequestBody
    @Validated
    form: CreateProject
  ): ResponseEntity<ViewProject> {

    val user = getSessionUser()
    val project = projectConverter.convertForCreateWith(form, user!!.id!!)

    return if (projectService.insertProject(project))
      ResponseEntity(HttpStatus.NO_CONTENT)
    else
      ResponseEntity(HttpStatus.BAD_REQUEST)
  }

  @PutMapping("/{id}/edit")
  fun edit(
    @RequestBody
    @Validated
    form: ViewProject
  ): ResponseEntity<ViewProject> {

    val user = getSessionUser()
    val project = projectConverter.convertForEntityWith(form, user!!.id!!)

    return if (projectService.editProject(project))
     ResponseEntity(HttpStatus.NO_CONTENT)
    else
      ResponseEntity(HttpStatus.BAD_REQUEST)
  }
}