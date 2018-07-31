package com.example.specsheetmanager.repository.mock

import com.example.specsheetmanager.domain.Project
import com.example.specsheetmanager.repository.ProjectRepository
import com.example.specsheetmanager.util.getSessionUser
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ProjectRepositoryMock: ProjectRepository {

    override
    fun create(project: Project) {

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

    override fun findByUserIdAndProjectId(userId: Int, projectId: Int): Project {

        return Project(
                projectId,
                "project${projectId.toString()}",
                Date(),
                Date(),
                "java",
                "spring",
                "mysql",
                "test_project_${projectId.toString()}",
                userId
        )
    }

    override fun update(project: Project) {
    }

}