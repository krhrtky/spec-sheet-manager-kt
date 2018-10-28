package com.example.specsheetmanager.controller.web

import com.example.specsheetmanager.controller.web.form.AddProjectForm
import com.example.specsheetmanager.controller.web.form.EditProjectForm
import com.example.specsheetmanager.controller.web.form.PrintTargetProjectForm
import com.example.specsheetmanager.service.ProjectService
import com.example.specsheetmanager.util.getSessionUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/projects")
class ProjectsController(
  @Autowired
  private val projectService: ProjectService
) {

  @ModelAttribute
  fun setUpProjectForm(): AddProjectForm = AddProjectForm()

  @ModelAttribute
  fun setUpPrintProjectForm(): PrintTargetProjectForm = PrintTargetProjectForm()

  @RequestMapping("/new")
  fun new(): String = "projects/new"

  @PostMapping("/add")
  fun addProject(
    @Validated
    form: AddProjectForm,
    bindingResult: BindingResult,
    model: Model
  ): String {

    if(bindingResult.hasErrors()) return "projects/new"

    val user = getSessionUser() ?: throw IllegalAccessException("session情報にユーザーがありません。")

    return if(projectService.insertProject(form, user.id!!)) showProjects(model) else "projects/new"
  }

  @GetMapping("")
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

    if(bindingResult.hasErrors()) return "/projects/{id}/edit"

    return if(projectService.editProject(form, user.id!!)) "redirect:/projects/" else "projects/{id}/edit"
  }

  @PostMapping("/print")
  fun print(
    form: PrintTargetProjectForm,
    model: Model
  ): String {

    if (form.printTargetProjectIdList.isEmpty()) return "/projects/list"

    val user = getSessionUser() ?: throw IllegalAccessException("session情報にユーザーがありません。")

    model.addAttribute(user)
    model.addAttribute(projectService.findByUserIdAndIdIn(user.id!!, form.printTargetProjectIdList.toList()))

    return "/projects/print"
  }
}