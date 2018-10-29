package com.example.specsheetmanager.service

import com.example.specsheetmanager.controller.api.form.ViewProject
import com.example.specsheetmanager.domain.Project

class ProjectConverterForView {

  companion object {
      fun convertWith(projectionList: List<Project>): List<ViewProject> {
        return projectionList
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
      }
  }
}