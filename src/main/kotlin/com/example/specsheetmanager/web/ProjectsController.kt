package com.example.specsheetmanager.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("projects")
class ProjectsController {

    @RequestMapping("/new")
    fun new(): String {
        return "projects/new"
    }

}