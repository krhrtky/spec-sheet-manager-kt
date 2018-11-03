package com.example.specsheetmanager.service

import com.example.specsheetmanager.domain.Project
import com.example.specsheetmanager.form.api.CreateProject
import com.example.specsheetmanager.form.api.ViewProject
import org.springframework.stereotype.Service

@Service
class ProjectConverter {

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
    name = viewProject.name!!,
    startDate = viewProject.startDate!!,
    endDate = viewProject.endDate!!,
    language = viewProject.language!!,
    framework = viewProject.framework!!,
    middleware = viewProject.middleware!!,
    about = viewProject.about!!,
    userId = userId
  )

  fun convertForCreateWith(createProject: CreateProject, userId: Int) = Project(
    name = createProject.name!!,
    startDate = createProject.startDate!!,
    endDate = createProject.endDate!!,
    language = createProject.language!!,
    framework = createProject.framework!!,
    middleware = createProject.middleware!!,
    about = createProject.about!!,
    userId = userId
  )
}