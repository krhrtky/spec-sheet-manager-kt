package com.example.specsheetmanager.domain

import com.example.specsheetmanager.web.form.AddProjectForm
import com.example.specsheetmanager.web.form.EditProjectForm
import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class Project(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  val id: Int? = null,
  @Column(name = "name")
  val name: String = "",
  @Column(name = "start_date")
  val startDate: Date = Date(),
  @Column(name = "end_date")
  val endDate: Date = Date(),
  @Column(name = "language")
  val language: String = "",
  @Column(name = "framework")
  val framework: String = "",
  @Column(name = "middleware")
  val middleware: String = "",
  @Column(name = "about")
  val about: String = "",
  @Column(name = "user_id")
  val userId: Int = 0
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
        id!!,
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
