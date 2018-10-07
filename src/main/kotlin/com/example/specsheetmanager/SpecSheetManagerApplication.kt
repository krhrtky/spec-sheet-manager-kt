package com.example.specsheetmanager

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class SpecSheetManagerApplication: CommandLineRunner {

  @Bean
  fun passwordEncoder(): PasswordEncoder {
    return BCryptPasswordEncoder()
  }

  override fun run(vararg args: String?) {
  }
}

fun main(args: Array<String>) {
  runApplication<SpecSheetManagerApplication>(*args)
}
