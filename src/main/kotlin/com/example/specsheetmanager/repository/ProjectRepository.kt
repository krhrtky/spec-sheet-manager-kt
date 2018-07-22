package com.example.specsheetmanager.repository

import com.example.specsheetmanager.domain.Project
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository {
    fun create(project: Project)
}