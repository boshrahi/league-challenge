package com.boshra.league.gradle.plugins

import com.boshra.league.gradle.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project

class LibraryJvmPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("org.jetbrains.kotlin.jvm")
      }
      configureKotlinJvm()
    }
  }
}
