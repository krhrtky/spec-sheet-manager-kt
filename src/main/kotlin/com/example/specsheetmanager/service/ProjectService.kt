package com.example.specsheetmanager.service

import com.example.specsheetmanager.domain.Project
import com.example.specsheetmanager.repository.ProjectRepository
import com.example.specsheetmanager.web.form.AddProjectForm
import com.example.specsheetmanager.web.form.EditProjectForm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProjectService(
        @Autowired
        private val projectRepository: ProjectRepository
) {
    fun insertProject(form: AddProjectForm, userId: Int): Boolean = try {
        projectRepository.save(Project.convertFromAddForm(form, userId))
        true

    } catch (e: Exception) {
        false
    }

    fun findProjectList(userId: Int): List<Project> = projectRepository.findByUserId(userId)

    fun findByUserIdProjectId(userId: Int, projectId: Int): Project = projectRepository.findByUserIdAndId(userId, projectId)

    fun editProject(form: EditProjectForm, userId: Int): Boolean = try {
        projectRepository.save(Project.convertFromEdit(form, userId))
        true

    } catch (e: Exception) {
        false
    }
}
