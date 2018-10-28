package com.example.specsheetmanager.service

import com.example.specsheetmanager.controller.web.form.AddProjectForm
import com.example.specsheetmanager.controller.web.form.EditProjectForm
import com.example.specsheetmanager.domain.Project
import com.example.specsheetmanager.repository.ProjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProjectService(
  @Autowired
  private val projectRepository: ProjectRepository
) {
  fun insertProject(form: AddProjectForm, userId: Int)= try {
    projectRepository.save(Project.convertFromAddForm(form, userId))
    true

  } catch (e: Exception) {
    e.printStackTrace()
    false
  }

  fun findProjectList(userId: Int) = projectRepository.findByUserId(userId)

  fun findByUserIdProjectId(userId: Int, projectId: Int) = projectRepository.findByUserIdAndId(userId, projectId)

  fun editProject(form: EditProjectForm, userId: Int) = try {
    projectRepository.save(Project.convertFromEdit(form, userId))
    true

  } catch (e: Exception) {
    e.printStackTrace()
    false
  }

  fun findByUserIdAndIdIn(userId:Int, id: List<Int>) = projectRepository.findByUserIdAndIdIn(userId, id)
}
