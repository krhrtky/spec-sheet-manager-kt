package com.example.specsheetmanager.domain

import com.example.specsheetmanager.web.form.AddProjectForm
import com.example.specsheetmanager.web.form.EditProjectForm
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
) {

    companion object {
        fun convertFromAddForm(form: AddProjectForm, userId: Int): Project {
            val (
                    name,
                    startDate,
                    endDate,
                    language,
                    framework,
                    middleware,
                    about
            ) = form

            return Project(
                    null,
                    name?: "",
                    startDate?: Date(),
                    endDate?: Date(),
                    language?: "",
                    framework?: "",
                    middleware?: "",
                    about?: "",
                    userId
            )
        }

        fun convertFromEdit(form: EditProjectForm, userId: Int): Project {
            val (
                    id,
                    name,
                    startDate,
                    endDate,
                    language,
                    framework,
                    middleware,
                    about
            ) = form

            return Project(
                    null,
                    name?: "",
                    startDate?: Date(),
                    endDate?: Date(),
                    language?: "",
                    framework?: "",
                    middleware?: "",
                    about?: "",
                    userId
            )
        }
    }
}
