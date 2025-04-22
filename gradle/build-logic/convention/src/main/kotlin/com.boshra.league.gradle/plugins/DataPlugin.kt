package com.boshra.league.gradle.plugins

import com.boshra.league.gradle.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class DataPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      pluginManager.apply {
        apply("com.boshra.league.gradle.android.library")
        apply("com.boshra.league.gradle.android.hilt")
      }

      dependencies {
        add("implementation", project(":data:model"))
        add("testImplementation", libs.findLibrary("junit").get())
        add("androidTestImplementation", libs.findLibrary("androidx-test-ext-junit").get())
      }
    }
  }
}
