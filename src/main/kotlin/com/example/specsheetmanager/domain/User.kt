package com.example.specsheetmanager.domain

data class User(
        val id: Int?,
        val name: String,
        val email: String,
        val password: String,
        val projectList: List<Project> = emptyList()
)