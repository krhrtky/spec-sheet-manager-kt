package com.example.specsheetmanager.domain

import java.util.Date

data class Project(
        val id: Int?,
        val name: String,
        val startDate: Date,
        val endDate: Date,
        val language: String,
        val framework: String,
        val middleware: String,
        val about: String,
        val userId: Int
)