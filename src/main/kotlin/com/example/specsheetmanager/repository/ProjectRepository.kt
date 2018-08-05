package com.example.specsheetmanager.repository

import com.example.specsheetmanager.domain.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository: JpaRepository<Project, Int> {

    fun findByUserId(userId: Int): List<Project>

    fun findByUserIdAndId(userId: Int, projectId: Int): Project

}
