package com.example.specsheetmanager.service

import com.example.specsheetmanager.domain.Project
import com.example.specsheetmanager.domain.convertToProjectFromForm
import com.example.specsheetmanager.repository.ProjectRepository
import com.example.specsheetmanager.web.form.ProjectForm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProjectService(
        @Autowired
        private val projectRepository: ProjectRepository
) {
    fun insertProject(form: ProjectForm, userId: Int): Boolean {
        return try {
            projectRepository.create(convertToProjectFromForm(form, userId))
            true
        } catch (e: Exception) {
            false
        }
    }

    fun findProjectList(userId: Int): List<Project> {
        return projectRepository.findProjectList(userId)
    }

    fun findByUserIdProjectId(userId: Int, projectId: Int): Project {
        return projectRepository.findByUserIdAndProjectId(userId, projectId)
    }

    fun editProject(form: ProjectForm, userId: Int): Boolean {
        return try {
            projectRepository.update(convertToProjectFromForm(form, userId))
            true
        } catch (e: Exception) {
            false
        }

    }
}