package com.example.specsheetmanager.repository.mock

import com.example.specsheetmanager.domain.Project
import com.example.specsheetmanager.repository.ProjectRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ProjectRepositoryMock: ProjectRepository {

    override fun create(project: Project) {

    }

    override fun findProjectList(userId: Int): List<Project> {
        return (0..userId)
                .asSequence()
                .map { Project(
                        it,
                        "project${it.toString()}",
                        Date(),
                        Date(),
                        "java",
                        "spring",
                        "mysql",
                        "test_project_${it.toString()}",
                        userId
                ) }
                .toList()
    }
}