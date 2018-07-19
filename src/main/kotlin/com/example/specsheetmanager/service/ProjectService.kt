package com.example.specsheetmanager.service

import com.example.specsheetmanager.domain.convertToProjectFromForm
import com.example.specsheetmanager.repository.ProjectRepository
import com.example.specsheetmanager.web.form.AddProjectForm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProjectService(
        @Autowired
        private val projectRepository: ProjectRepository
) {
        fun insertProject(form: AddProjectForm, userId: Int): Boolean {
                return try {
                    projectRepository.create(convertToProjectFromForm(form, userId))
                    true
                } catch (e: Exception) {
                    false
                }
        }
}