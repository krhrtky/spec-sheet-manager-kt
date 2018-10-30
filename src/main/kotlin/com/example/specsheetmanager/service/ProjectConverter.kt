package com.example.specsheetmanager.service

import com.example.specsheetmanager.domain.Project
import com.example.specsheetmanager.form.api.ViewProject

class ProjectConverter {

  companion object {
    fun convertForViewWith(projectionList: List<Project>) = projectionList
      .asSequence()
      .map {
        val project = ViewProject()
        project.id = it.id
        project.name = it.name
        project.startDate = it.startDate
        project.endDate = it.endDate
        project.about = it.about
        project.framework = it.framework
        project.middleware = it.middleware
        project.language = it.language

        project
      }
      .toList()

    fun convertForEntityWith(viewProject: ViewProject, userId: Int) = Project(
      id = viewProject.id,
      name = viewProject.name!!,
      startDate = viewProject.startDate!!,
      endDate = viewProject.endDate!!,
      language = viewProject.language!!,
      framework = viewProject.framework!!,
      middleware = viewProject.middleware!!,
      about = viewProject.about!!,
      userId = userId
    )
  }
}