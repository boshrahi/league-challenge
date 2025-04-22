package com.boshra.league.gradle.plugins

import com.boshra.league.gradle.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class FeaturePlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      pluginManager.apply {
        apply("com.boshra.league.gradle.android.library")
        apply("com.boshra.league.gradle.android.hilt")
        apply(libs.findPlugin("compose-plugin").get().get().pluginId)
      }

      dependencies {
        add("implementation", project(":data:model"))
        add("implementation", project(":core"))

        add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
        add("implementation", libs.findLibrary("lifecycle.runtime.compose").get())
        add("implementation", libs.findLibrary("lifecycle.viewmodel.compose").get())
        add("implementation", libs.findLibrary("coil-compose").get())
        add("implementation", libs.findLibrary("material3").get())

        add("testImplementation", libs.findLibrary("junit").get())
        add("androidTestImplementation", libs.findLibrary("androidx-test-ext-junit").get())
        add("androidTestImplementation", libs.findLibrary("espresso-core").get())
      }
    }
  }
}
