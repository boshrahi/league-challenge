package com.boshra.league.gradle.plugins

import com.boshra.league.gradle.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

class HiltPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("org.jetbrains.kotlin.kapt")
        apply("dagger.hilt.android.plugin")
      }
      extensions.configure<KaptExtension>("kapt") {
        correctErrorTypes = true
      }

      dependencies {
        "implementation"(libs.findLibrary("hilt-android").get())
        "kapt"(libs.findLibrary("dagger-hilt-android-compiler").get())
      }
    }
  }
}
