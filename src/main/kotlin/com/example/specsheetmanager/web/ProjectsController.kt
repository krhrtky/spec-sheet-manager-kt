package com.example.specsheetmanager.web

import com.example.specsheetmanager.service.ProjectService
import com.example.specsheetmanager.util.getSessionUser
import com.example.specsheetmanager.web.form.AddProjectForm
import com.example.specsheetmanager.web.form.EditProjectForm
import com.example.specsheetmanager.web.form.PrintTargetProjectForm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/projects")
class ProjectsController(
  @Autowired
  private val projectService: ProjectService
) {

  @ModelAttribute
  fun setUpProjectForm() = AddProjectForm()

  @ModelAttribute
  fun setUpPrintProjectForm() = PrintTargetProjectForm()

  @RequestMapping("/new")
  fun new() = "projects/new"

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
  fun BindingResult.edit(
    @Validated
    form: EditProjectForm,
    model: Model
  ): String {

    val user = getSessionUser() ?: throw IllegalAccessException("session情報にユーザーがありません。")

    if(hasErrors()) return "/projects/{id}/edit"

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
