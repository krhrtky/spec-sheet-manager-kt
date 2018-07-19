package com.example.specsheetmanager.repository.mock

import com.example.specsheetmanager.domain.Project
import com.example.specsheetmanager.repository.ProjectRepository
import org.springframework.stereotype.Repository

@Repository
class ProjectRepositoryMock: ProjectRepository {

    override fun create(project: Project) {

    }
}